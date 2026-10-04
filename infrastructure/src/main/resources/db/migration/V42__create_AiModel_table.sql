CREATE TABLE IF NOT EXISTS ${db_schema}.ai_models (
    id UUID PRIMARY KEY,
    provider_model_id UUID NOT NULL,
    name_model VARCHAR(100) NOT NULL,
    model_key VARCHAR(120) NOT NULL,
    input_token_price DECIMAL(12,8) NOT NULL,
    output_token_price DECIMAL(12,8) NOT NULL,
    max_tokens INTEGER NOT NULL,
    context_window INTEGER NOT NULL,
    is_active BOOLEAN NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT fk_ai_models_provider_model_id FOREIGN KEY (provider_model_id) REFERENCES ${db_schema}.provider_models_ai (id)
);

CREATE INDEX IF NOT EXISTS idx_ai_models_provider_model_id ON ${db_schema}.ai_models (provider_model_id);
