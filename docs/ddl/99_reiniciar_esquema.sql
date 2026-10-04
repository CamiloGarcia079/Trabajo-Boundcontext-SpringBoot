-- CUIDADO: borra todo el esquema con sus tablas y datos.
-- Solo para desarrollo. Como la tabla de historial de Flyway vive dentro del esquema,
-- al borrarlo Flyway también "olvida" lo que ejecutó y vuelve a correr V1..V52 al arrancar.

DROP SCHEMA IF EXISTS mindconnect_schema CASCADE;
