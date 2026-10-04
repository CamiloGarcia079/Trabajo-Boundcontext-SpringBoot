-- Paso 1: crear la base de datos.
-- Se ejecuta conectado a la base "postgres" (no dentro de mindconnectdb, porque todavía no existe).
-- CREATE DATABASE no puede correr dentro de una transacción.

CREATE DATABASE mindconnectdb
    WITH ENCODING = 'UTF8';
