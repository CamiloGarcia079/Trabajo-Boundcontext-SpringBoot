# MindConnect

Backend de MindConnect en Spring Boot con arquitectura hexagonal y enfoque DDD (bounded contexts), siguiendo la estructura del proyecto `demo-ddd` que hicimos en clase: tres módulos de Maven. Tiene las 52 tablas del diagrama; cada tabla es un bounded context con su migración de Flyway y un CRUD listo.

## Módulos

| Módulo | Para qué sirve | Depende de |
|---|---|---|
| `domain` | El agregado de cada tabla (una clase que extiende `AggregateRoot`), su id como value object, sus eventos de dominio, su excepción y el puerto del repositorio. Sin Spring ni JPA. | nadie |
| `application` | Los comandos, la respuesta, la excepción y los casos de uso (uno por operación). Tampoco usa Spring. | `domain` |
| `infrastructure` | Controladores REST, entidades JPA, mappers, adaptadores, Flyway y configuración. Aquí está la clase que arranca la app. | `application` |

La dependencia siempre va hacia adentro: infraestructura conoce a aplicación, aplicación conoce al dominio, y el dominio no conoce a nadie.

## Un bounded context por tabla

Cada tabla tiene su carpeta en los tres módulos. Así queda `countries`:

```
domain/.../country
    model/aggregate/Country                          agregado raíz (register, restore, update)
    model/valueobject/CountryId                      el id, como value object
    event/CountryRegisteredEvent, CountryUpdatedEvent, CountryDeletedEvent
    exception/CountryNotFoundException
    port/repository/CountryRepository                puerto de salida (interfaz)

application/.../country
    command/RegisterCountryCommand, UpdateCountryCommand
    dto/CountryResponse                              lo que devuelve la API
    exception/CountryNotFoundApplicationException
    usecase/RegisterCountryUseCase, GetCountryByIdUseCase, ListCountryUseCase,
            UpdateCountryUseCase, DeleteCountryUseCase

infrastructure/.../country
    adapters/in/rest/controllers/CountryController
    adapters/in/rest/dtos/CreateCountryRequest, UpdateCountryRequest
    adapters/in/rest/exceptionhandlers/CountryExceptionHandler
    adapters/out/persistence/entity/CountryJpaEntity
    adapters/out/persistence/mappers/CountryPersistenceMapper
    adapters/out/persistence/repositories/CountryJpaRepository, CountryRepositoryAdapter
    config/CountryBeansConfig                        crea los casos de uso y los conecta con su repositorio
```

Las 52 tablas siguen exactamente el mismo molde. La lista completa, con paquete, endpoint y migración de cada una, está en [`docs/tablas.md`](docs/tablas.md).

Además hay un paquete `common` en cada módulo: en dominio, `AggregateRoot`, `DomainEvent`, `DomainException` y `DomainValidations`; en aplicación, `ApplicationException`; en infraestructura, el manejador global de errores y el `ErrorResponse`. El `CorsConfig` está en `infrastructure/config`.

### Cómo viaja una petición

`POST /api/countries` entra por `CountryController`, que valida el `CreateCountryRequest` y lo convierte en un `RegisterCountryCommand`. `RegisterCountryUseCase` llama a `Country.register(...)`, que genera el id, pone las fechas y deja el evento `CountryRegisteredEvent`, y guarda el agregado con `CountryRepository`. Ese repositorio es una interfaz del dominio; quien la implementa es `CountryRepositoryAdapter`, que usa el `CountryPersistenceMapper` y el repositorio de Spring Data para guardar la `CountryJpaEntity`. El caso de uso nunca sabe que existe JPA.

### Relaciones entre contextos

Un agregado no guarda objetos de otro contexto: guarda solo su `UUID`. Por ejemplo, `Patient` tiene `documentTypeId`, `cityId` o `createdBy`, y en el Javadoc de cada agregado quedan listadas sus relaciones (`cityId -> CityMunicipality`). Quien garantiza que ese id exista es la llave foránea de la base de datos (si no existe, la API responde 409).

## Requisitos

- JDK 25
- Maven 3.9 o superior
- PostgreSQL en `localhost:5432`
- Un archivo `.env` con tus credenciales: copia `.env.example` como `.env` y completa `DB_URL`, `DB_USERNAME` y `DB_PASSWORD`. El `.env` está en `.gitignore`, no se sube al repositorio.

## Base de datos

- Base de datos: `mindconnectdb`
- Esquema: `mindconnect_schema`
- Historial de Flyway: `flyway_schema_history_mindconnect`

Hay dos formas de dejarla lista.

**1. Con Flyway (la normal).** Solo se crea la base de datos:

```sql
CREATE DATABASE mindconnectdb;
```

Al arrancar, Flyway crea el esquema (`create-schemas` más `init-sqls`) y corre las migraciones de `V1` a `V52`. En los `.sql` el esquema se escribe como `${db_schema}`, que Flyway reemplaza con el valor de `placeholders.db_schema`.

**2. A mano con los scripts DDL**, desde pgAdmin o psql, en este orden:

| Script | Qué hace |
|---|---|
| `docs/ddl/01_crear_base_de_datos.sql` | `CREATE DATABASE` (conectado a `postgres`) |
| `docs/ddl/02_crear_esquema.sql` | `CREATE SCHEMA` |
| `docs/ddl/03_crear_tablas.sql` | Las 52 tablas con sus llaves e índices |
| `docs/ddl/99_reiniciar_esquema.sql` | `DROP SCHEMA ... CASCADE`, solo para empezar de cero en desarrollo |

Los comandos DDL (`CREATE`, `ALTER`, `DROP`, `TRUNCATE`, `COMMENT`) están explicados con ejemplos de este proyecto en [`docs/comandos-ddl.md`](docs/comandos-ddl.md).

### Las migraciones

Una por tabla, con el nombre `V<número>__create_<Entidad>_table.sql`, en el orden en que se pueden crear (primero las tablas de las que otras dependen).

| Migraciones | Tablas |
|---|---|
| V1 a V3 | Ubicación: `countries`, `state_regions`, `city_municipalities` |
| V4 a V19 | Catálogos de la parte clínica: tipos de documento, géneros, tipos de encuentro, niveles de riesgo, estados de tratamiento, vías de medicación, sistemas de diagnóstico, etc. |
| V20 a V21 | `professionals`, `professional_studies` |
| V22 a V24 | `contacts`, `phone_contacts`, `email_contacts` |
| V25 a V27 | `patients`, `patient_allergies`, `patient_contacts` |
| V28 a V34 | Historia clínica: `clinical_records`, `encounters`, `clinical_notes`, `mental_status_exams`, `risk_assessments`, `treatment_plans`, `treatment_goals` |
| V35 a V40 | Catálogos del chat: prioridades, estados, tipos de remitente y de mensaje |
| V41 a V42 | `provider_models_ai`, `ai_models` |
| V43 a V52 | Chat: conversaciones, participantes, mensajes, ejecuciones de IA, métricas, errores y escalamientos |

Siguen el mismo molde del `V1__create_Country_table.sql` de clase: `CREATE TABLE IF NOT EXISTS ${db_schema}.tabla`, `id UUID PRIMARY KEY` y las fechas como `TIMESTAMP WITHOUT TIME ZONE NOT NULL`. A eso se le suman las llaves foráneas y los `UNIQUE` con nombre, más un índice por cada llave foránea (Postgres no los crea solo).

## Perfiles y configuración

Los archivos están en `infrastructure/src/main/resources`:

- `application.yml`: nombre de la app, perfil activo (`dev`) y puerto 8081.
- `application-dev.yml`: conexión a PostgreSQL (toma `DB_URL`, `DB_USERNAME` y `DB_PASSWORD` del archivo `.env`), JPA con `ddl-auto: none` (el esquema lo manejan las migraciones) y Flyway encendido. Incluye CORS y la propiedad `queue.block-users.fixed-delay-ms` del proyecto de clase.
- `application-jpa.yml`: el JPA "tradicional" de la captura de clase. Apaga Flyway y deja que Hibernate cree las tablas desde las entidades (`create-drop`). Se usa junto con `dev`: `-Dspring-boot.run.profiles=dev,jpa`. Ojo: `create-drop` borra las tablas al arrancar y al apagar, así que solo sirve con una base de pruebas. Además, como las entidades guardan las llaves foráneas como UUID simples, en este modo no se crean las llaves foráneas que sí crean las migraciones.
- `application-prod.yml`: lo mismo que `dev`, pero la conexión sale de variables de entorno (`DB_URL`, `DB_USERNAME`, `DB_PASSWORD`, `CORS_ALLOWED_ORIGINS`). Hay un `.env.example` de referencia.

## Cómo correrlo

Desde la raíz:

```bash
mvn clean install
mvn spring-boot:run -pl infrastructure
```

Queda en http://localhost:8081. En VS Code también sirve `.vscode/launch.json`.

## La API

Cada tabla expone el mismo CRUD bajo `/api/<tabla-con-guiones>`:

| Método | Ruta | Qué hace |
|---|---|---|
| POST | `/api/countries` | Crea (devuelve 201) |
| GET | `/api/countries` | Lista |
| GET | `/api/countries/{id}` | Busca por id |
| PUT | `/api/countries/{id}` | Reemplaza los datos |
| DELETE | `/api/countries/{id}` | Elimina (devuelve 204) |

Los campos del JSON van en camelCase (`nameCountry`, no `name_country`). El `id` y las fechas `createdAt` y `updatedAt` los pone el servidor, no se envían. Tampoco se envía `active` / `isActive`: el agregado lo deja en `true` al registrar. Al actualizar (PUT) no cambian `createdAt` ni `createdBy`. Un ejemplo:

```bash
curl -X POST http://localhost:8081/api/countries \
  -H "Content-Type: application/json" \
  -d '{"nameCountry":"Colombia","codeCountry":"CO","description":"República de Colombia","telephonePrefix":"+57"}'
```

Los errores salen en un mismo formato (`status`, `error`, `message`, `timestamp`):

| Caso | Código |
|---|---|
| Falta un campo obligatorio o pasa del largo permitido | 400 |
| El id no existe | 404 |
| Valor duplicado (un `UNIQUE`) o llave foránea que no existe o que aún se usa | 409 |

Como las tablas se relacionan por llaves foráneas, hay que crear primero las de las que otras dependen. Por ejemplo, un `state_region` necesita el id de un `country` que ya exista.

## Pruebas

```bash
mvn test
```

`MindConnectApplicationTests` levanta el contexto completo: Flyway valida y aplica las migraciones, así que necesita PostgreSQL encendido y el `.env` listo.

El resto de las pruebas corre sin Spring ni base de datos. Cada bounded context trae tres clases:

- `<Entidad>Test` (dominio): que `register` genere el id y los valores por defecto y deje el evento de registro, que `update` cambie los datos y deje el evento de actualización, y que un campo obligatorio nulo lance `DomainException`.
- `Delete<Entidad>UseCaseTest` (aplicación): que elimine un registro existente y devuelva el evento `Deleted`, y que falle si el id no existe.
- `<Entidad>UseCasesTest` (aplicación): registrar, buscar por id, listar y actualizar, con sus casos de id inexistente, usando un repositorio falso en memoria.

En el dominio hay además pruebas de `AggregateRoot` y de `DomainValidations`.

## Qué se interpretó del diagrama

El diagrama dejaba varios detalles abiertos. Quedó así:

- **`NOT NULL`**: toda columna sin la marca `N` es obligatoria. Algunas probablemente deberían aceptar nulos (por ejemplo `clinical_records.closed_at`, `encounters.ended_at`, `clinical_notes.signed_at`, `treatment_plans.end_date`, `treatment_goals.completed_at`). Se dejaron como dice el diagrama; para cambiarlas va una migración nueva con `ALTER TABLE ... DROP NOT NULL` y se quita la validación en el `Request`.
- **`created_by`, `updated_by` y `recorded_by`**: apuntan a `professionals` (también en `encounters` y `patient_allergies.recorded_by`). Solo `chat_conversations.closed_by` queda como `UUID` sin restricción.
- **`professionals`**: el diagrama marca como únicos `document_number`, `first_name`, `last_name` y `license_number`. Se hicieron únicos `document_number` y `license_number`; nombre y apellido por separado no, porque no podrían existir dos profesionales llamados igual.
- **`phone_contacts`**: `Column1` y `Column2` no tienen tipo y parecen sobrantes, así que no se crearon. `phone` acepta nulo y `notes` es obligatorio, como lo marca el diagrama.
- **`city_municipalities.code_city`**: se corrigió el nombre del diagrama (`code_citi`).
- **`ai_models.provider_model_id`**: en el diagrama es `VARCHAR(50)`, pero apunta a un `UUID`, así que se creó como `UUID`.
- **`provider_models_ai`**: `isActive` quedó como `is_active` (como el resto del proyecto) y `razon_social`, que no tenía longitud, como `VARCHAR(150)`.
- **`TIMESTAMPT`** (con la T de más en el diagrama) se tomó como `TIMESTAMP`.
- **Nombres de columna**: se dejaron como en el diagrama, aunque tengan detalles raros: `gender_identity`, `professional_type` y `treatment_goal_id` (esta última apunta a `treatment_goal_statuses`).
- **`chat_messages.content` y `metadata`** son `JSONB` en la base y `String` en Java (llegan y salen como texto JSON).

## Decisiones del código

- **Llaves foráneas como UUID.** Los agregados y las entidades JPA guardan las llaves foráneas como `UUID`, sin `@ManyToOne`. Así cada contexto se relaciona con los otros solo por id, sin cargar objetos de otras tablas. Si más adelante se quieren las relaciones de `@ManyToOne`, se agregan en las entidades de cada tabla sin tocar el dominio ni los casos de uso.
- **Agregado separado de la entidad JPA.** El dominio no conoce JPA: el `PersistenceMapper` convierte entre el agregado y la entidad. Por eso `Country.restore(...)` reconstruye un registro de la base sin generar eventos, y `Country.register(...)` es la única forma de crear uno nuevo.
- **Eventos de dominio.** `register` y `update` dejan su evento dentro del agregado (`domainEvents()`); `DeleteXUseCase` devuelve el evento `Deleted`. Por ahora nadie los consume: quedan listos para publicarlos más adelante.
- **Mapeo a mano.** Los mapeos entre capas están escritos a mano (sin MapStruct), para que se vean y se puedan explicar. El `pom.xml` conserva las propiedades de versión de MapStruct y springdoc que traía el proyecto de clase.
- **Sin paginación ni seguridad.** El listado devuelve todo y no hay autenticación. Eso queda para una siguiente etapa.
