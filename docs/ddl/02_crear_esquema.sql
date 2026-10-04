-- Paso 2: crear el esquema donde van todas las tablas.
-- Se ejecuta ya conectado a mindconnectdb.
-- Si usas Flyway (create-schemas + init-sqls en application-dev.yml) este paso lo hace
-- la aplicación sola al arrancar; aquí queda por si se quiere hacer a mano.

CREATE SCHEMA IF NOT EXISTS mindconnect_schema;

COMMENT ON SCHEMA mindconnect_schema IS 'Tablas del proyecto MindConnect';
