# MindConnect

API REST hecha con Spring Boot y arquitectura hexagonal. Guarda los datos en PostgreSQL (52 tablas que crea Flyway al arrancar) y trae un CRUD para cada tabla.

## Qué necesitas

- JDK 25
- Maven 3.9 o superior
- PostgreSQL 14 o superior, o Docker

Revisa que `java -version` y `mvn -version` muestren Java 25. Si Maven usa otro Java, el proyecto no compila.

Si te falta algo:

- **Windows:** `winget install EclipseAdoptium.Temurin.25.JDK`. Maven se baja de https://maven.apache.org/download.cgi y se agrega al `PATH`.
- **macOS:** `brew install --cask temurin@25` y `brew install maven`.
- **Ubuntu/Debian:** `sudo apt install openjdk-25-jdk maven`.

## Cómo correrlo

Todo desde la carpeta que tiene el `pom.xml`.

**1. Base de datos.** La forma más fácil es Docker (con Docker Desktop abierto):

```bash
docker run --name mindconnect-db -e POSTGRES_PASSWORD=postgres123 -e POSTGRES_DB=mindconnectdb -p 5432:5432 -v mindconnect-data:/var/lib/postgresql/data -d postgres:16
```

Si ya tienes PostgreSQL instalado, solo crea la base: `CREATE DATABASE mindconnectdb;`. Las tablas no hay que crearlas, de eso se encarga la aplicación.

**2. Credenciales.** Copia la plantilla (`cp .env.example .env`, en Windows `copy .env.example .env`) y completa la contraseña:

```properties
DB_URL=jdbc:postgresql://localhost:5432/mindconnectdb
DB_USERNAME=postgres
DB_PASSWORD=postgres123
```

El archivo debe llamarse exactamente `.env` (en Windows cuida que no quede como `.env.txt`), estar junto al `pom.xml`, y no llevar comillas ni espacios alrededor del `=`.

**3. Compilar y arrancar.**

```bash
mvn clean install -DskipTests
mvn spring-boot:run -pl infrastructure
```

La primera vez tarda porque Maven descarga las librerías. Cuando el log diga `Started MindConnectApplication`, abre http://localhost:8081/api/countries. Debe responder `[]`.

Abrir solo `http://localhost:8081` da un 404, y es normal: no hay página de inicio, todo está bajo `/api`.

## Probar la API

Con la aplicación corriendo, en otra terminal:

```bash
# crear un país
curl -X POST http://localhost:8081/api/countries \
  -H "Content-Type: application/json" \
  -d '{"nameCountry":"Colombia","codeCountry":"CO","description":"Republica de Colombia","telephonePrefix":"+57"}'

# listarlos
curl http://localhost:8081/api/countries
```

La respuesta trae el `id` del país. Con ese id se puede crear una región en `/api/state-regions`, pasándolo como `countryId`.

En PowerShell `curl` es otra cosa: usa `curl.exe` o `Invoke-RestMethod`. También sirve Postman o cualquier cliente HTTP.

## La API

Cada tabla tiene los mismos cinco endpoints bajo `/api/<tabla-con-guiones>`:

| Método | Ruta | Respuesta |
|---|---|---|
| POST | `/api/countries` | 201 con el registro creado |
| GET | `/api/countries` | 200 con la lista |
| GET | `/api/countries/{id}` | 200 |
| PUT | `/api/countries/{id}` | 200 con el registro actualizado |
| DELETE | `/api/countries/{id}` | 204 |

- Los campos van en camelCase (`nameCountry`, no `name_country`).
- El `id`, `createdAt` y `updatedAt` los pone el servidor, no se envían. Tampoco `isActive`: al crear queda en `true`.
- Si falta un campo o es inválido responde 400. Si el id no existe, 404. Si el valor está repetido, o apunta a un registro que no existe (o que otro todavía usa), 409.
- Como las tablas se relacionan con llaves foráneas, hay que crear primero lo que otras tablas referencian. El orden de las migraciones (V1 a V52) sirve de guía.
- No hay autenticación ni paginación todavía, así que no la expongas en internet tal cual.

Las 52 rutas están en [`docs/tablas.md`](docs/tablas.md).

## Pruebas

```bash
mvn test -pl domain,application   # más de 600 pruebas, no necesitan base de datos
mvn test                          # incluye MindConnectApplicationTests
```

`MindConnectApplicationTests` levanta Spring completo, así que necesita PostgreSQL encendido y el `.env` listo. Si no, falla, y es lo esperado.

## Base de datos

La base es `mindconnectdb` y el esquema `mindconnect_schema`. Las migraciones están en `infrastructure/src/main/resources/db/migration`, una por tabla (`V1__create_Country_table.sql` hasta `V52`).

Si prefieres crear todo a mano, en `docs/ddl/` hay scripts para `psql` o pgAdmin: `01_crear_base_de_datos.sql`, `02_crear_esquema.sql` y `03_crear_tablas.sql`, en ese orden. `99_reiniciar_esquema.sql` borra todo el esquema. Los comandos DDL están explicados en [`docs/comandos-ddl.md`](docs/comandos-ddl.md).

Para empezar de cero con Docker: `docker rm -f mindconnect-db`, `docker volume rm mindconnect-data`, y vuelves a crear el contenedor. Los datos se pierden.

Una regla importante: **no edites una migración que ya se aplicó.** Flyway guarda una huella de cada archivo y, si cambia, la aplicación no arranca. Cualquier cambio va en una migración nueva (`V53__...sql`).

## Perfiles

Están en `infrastructure/src/main/resources`.

- `dev`: el que se usa por defecto.
- `jpa`: apaga Flyway y deja que Hibernate cree las tablas desde las entidades. Se activa con `-Dspring-boot.run.profiles=dev,jpa`. Usa `create-drop`, que borra las tablas al arrancar y al apagar, así que solo sirve con una base de pruebas vacía.
- `prod`: toma todo de variables de entorno (`DB_URL`, `DB_USERNAME`, `DB_PASSWORD` y `CORS_ALLOWED_ORIGINS`). Se ejecuta con `java -jar infrastructure/target/infrastructure-1.0-SNAPSHOT.jar --spring.profiles.active=prod`.

Si el puerto 8081 está ocupado: `mvn spring-boot:run -pl infrastructure -Dspring-boot.run.arguments=--server.port=8082`.

## Cómo está organizado

Tres módulos de Maven, y la dependencia siempre va hacia adentro:

- `domain`: los agregados, sus eventos y los repositorios como interfaces. No usa Spring ni JPA.
- `application`: los casos de uso. Tampoco usa Spring.
- `infrastructure`: controladores REST, entidades JPA, Flyway, configuración y la clase que arranca la app.

Cada tabla tiene su propia carpeta en los tres módulos, todas con el mismo molde. Así queda `countries`:

```text
domain/.../country
    model/aggregate/Country, model/valueobject/CountryId
    event/        CountryRegisteredEvent, CountryUpdatedEvent, CountryDeletedEvent
    port/repository/CountryRepository
application/.../country
    command/      RegisterCountryCommand, UpdateCountryCommand
    usecase/      Register, GetById, List, Update, Delete
    dto/CountryResponse
infrastructure/.../country
    adapters/in/rest/       controllers, dtos, exceptionhandlers
    adapters/out/persistence/  entity, mappers, repositories
    config/CountryBeansConfig
```

Las llaves foráneas se guardan como `UUID` simples, sin `@ManyToOne`, y los mapeos entre capas están escritos a mano.

## Si algo falla

- **`release version 25 not supported`**: Maven usa un Java más viejo. Mira la línea `Java version` de `mvn -version` y ajusta `JAVA_HOME`.
- **`Could not resolve placeholder 'DB_URL'`**: no encuentra el `.env`. Revisa el nombre y que esté junto al `pom.xml`.
- **`Connection refused`**: PostgreSQL no está encendido o usa otro puerto. Con Docker, `docker ps` o `docker start mindconnect-db`. Si el 5432 está ocupado, crea el contenedor con `-p 5433:5432` y cambia el puerto en `DB_URL`.
- **`password authentication failed`**: la contraseña del `.env` no coincide. Con Docker, la contraseña queda fijada al crear el volumen; si la cambiaste, borra el contenedor y el volumen y créalos de nuevo.
- **`Migration checksum mismatch`**: se editó una migración ya aplicada. En desarrollo, empieza de cero (ver Base de datos).
- **`Port 8081 was already in use`**: usa otro puerto, como arriba.
