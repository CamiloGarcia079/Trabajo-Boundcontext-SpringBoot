# Comandos DDL de MindConnect

DDL significa *Data Definition Language*: los comandos que definen o cambian la **estructura** de la base de datos (bases, esquemas, tablas, columnas, índices). Los que trabajan con los datos (`INSERT`, `UPDATE`, `DELETE`, `SELECT`) son DML y son otra cosa.

Los cuatro comandos base son `CREATE`, `ALTER`, `DROP` y `TRUNCATE`. Abajo están con ejemplos sobre las tablas de este proyecto. Todos los bloques corren en orden sobre el esquema ya creado.

## CREATE: crear

La base de datos se crea conectado a `postgres`, no dentro de ella:

```sql
CREATE DATABASE mindconnectdb WITH ENCODING = 'UTF8';
```

Ya dentro de `mindconnectdb`, el esquema y una tabla de ejemplo:

```sql
CREATE SCHEMA IF NOT EXISTS mindconnect_schema;

CREATE TABLE IF NOT EXISTS mindconnect_schema.demo_tabla (
    id UUID PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL
);
```

Un índice sobre una columna que se consulta mucho:

```sql
CREATE INDEX IF NOT EXISTS idx_demo_tabla_nombre ON mindconnect_schema.demo_tabla (nombre);
```

## ALTER: modificar

Agregar, cambiar de tipo, renombrar y quitar columnas:

```sql
ALTER TABLE mindconnect_schema.demo_tabla ADD COLUMN descripcion TEXT;
ALTER TABLE mindconnect_schema.demo_tabla ALTER COLUMN nombre TYPE VARCHAR(100);
ALTER TABLE mindconnect_schema.demo_tabla RENAME COLUMN descripcion TO detalle;
ALTER TABLE mindconnect_schema.demo_tabla DROP COLUMN detalle;
```

Restricciones:

```sql
ALTER TABLE mindconnect_schema.demo_tabla ADD CONSTRAINT uq_demo_tabla_nombre UNIQUE (nombre);
ALTER TABLE mindconnect_schema.demo_tabla DROP CONSTRAINT uq_demo_tabla_nombre;
```

Casos reales de este proyecto. Por ejemplo, en el diagrama `encounters.ended_at` no dice que acepte nulos, pero una consulta que sigue abierta todavía no termina:

```sql
ALTER TABLE mindconnect_schema.encounters ALTER COLUMN ended_at DROP NOT NULL;
ALTER TABLE mindconnect_schema.patients ALTER COLUMN active SET DEFAULT TRUE;
```

Y para agregar una llave foránea que la tabla no tenía:

```sql
ALTER TABLE mindconnect_schema.encounters
    ADD CONSTRAINT fk_encounters_created_by FOREIGN KEY (created_by)
    REFERENCES mindconnect_schema.professionals (id);
```

## COMMENT: documentar

No es de los cuatro base, pero también es DDL y ayuda mucho:

```sql
COMMENT ON TABLE mindconnect_schema.patients IS 'Pacientes registrados en la plataforma';
COMMENT ON COLUMN mindconnect_schema.patients.gender_identity IS 'Identidad de género (apunta a genders)';
```

## TRUNCATE: vaciar

Borra todas las filas de una tabla de una vez, pero deja la tabla:

```sql
TRUNCATE TABLE mindconnect_schema.demo_tabla;
```

Si otras tablas la referencian con llaves foráneas hay que agregar `CASCADE`, y eso vacía también las que dependen de ella.

## DROP: eliminar

```sql
DROP INDEX IF EXISTS mindconnect_schema.idx_demo_tabla_nombre;
DROP TABLE IF EXISTS mindconnect_schema.demo_tabla;
```

Para borrar todo el esquema con sus tablas (solo en desarrollo):

```sql
DROP SCHEMA IF EXISTS mindconnect_schema CASCADE;
```

Y la base de datos, conectado a `postgres`:

```sql
DROP DATABASE IF EXISTS mindconnectdb;
```

## Cómo se lleva esto con Flyway

Flyway guarda un *checksum* de cada migración que ya ejecutó. Si editas un `V*.sql` viejo, la aplicación se niega a arrancar porque el archivo ya no coincide con lo registrado.

Por eso, una vez que una migración corrió, **no se toca**: cualquier cambio a la estructura se escribe como una migración nueva (`V53__...sql`) con su `ALTER TABLE`. Mientras el proyecto sea solo local, la salida rápida es `docs/ddl/99_reiniciar_esquema.sql`, que borra el esquema (y con él el historial de Flyway) para que todo se cree de nuevo.

Un ejemplo de migración nueva:

```sql
-- V53__relax_Encounter_ended_at.sql
ALTER TABLE ${db_schema}.encounters ALTER COLUMN ended_at DROP NOT NULL;
```
