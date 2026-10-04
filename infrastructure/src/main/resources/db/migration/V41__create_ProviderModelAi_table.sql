CREATE TABLE IF NOT EXISTS ${db_schema}.provider_models_ai (
    id UUID PRIMARY KEY,
    name_provider_ai VARCHAR(100) NOT NULL,
    razon_social VARCHAR(150) NOT NULL,
    sitio_web TEXT NOT NULL,
    is_active BOOLEAN NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITHOUT TIME ZONE NOT NULL
);
