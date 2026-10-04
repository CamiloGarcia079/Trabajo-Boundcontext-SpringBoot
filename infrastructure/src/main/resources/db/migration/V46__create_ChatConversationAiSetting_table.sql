CREATE TABLE IF NOT EXISTS ${db_schema}.chat_conversation_ai_settings (
    id UUID PRIMARY KEY,
    conversation_id UUID NOT NULL,
    ai_enabled BOOLEAN NOT NULL,
    default_model_id UUID NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT fk_chat_conversation_ai_settings_conversation_id FOREIGN KEY (conversation_id) REFERENCES ${db_schema}.chat_conversations (id),
    CONSTRAINT fk_chat_conversation_ai_settings_default_model_id FOREIGN KEY (default_model_id) REFERENCES ${db_schema}.ai_models (id)
);

CREATE INDEX IF NOT EXISTS idx_chat_conversation_ai_settings_conversation_id ON ${db_schema}.chat_conversation_ai_settings (conversation_id);
CREATE INDEX IF NOT EXISTS idx_chat_conversation_ai_settings_default_model_id ON ${db_schema}.chat_conversation_ai_settings (default_model_id);
