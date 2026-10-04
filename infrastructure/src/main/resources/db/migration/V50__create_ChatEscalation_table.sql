CREATE TABLE IF NOT EXISTS ${db_schema}.chat_escalations (
    id UUID PRIMARY KEY,
    conversation_id UUID NOT NULL,
    status_id UUID NOT NULL,
    from_ai BOOLEAN NOT NULL,
    reason TEXT NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT fk_chat_escalations_conversation_id FOREIGN KEY (conversation_id) REFERENCES ${db_schema}.chat_conversations (id),
    CONSTRAINT fk_chat_escalations_status_id FOREIGN KEY (status_id) REFERENCES ${db_schema}.escalations_statuses (id)
);

CREATE INDEX IF NOT EXISTS idx_chat_escalations_conversation_id ON ${db_schema}.chat_escalations (conversation_id);
CREATE INDEX IF NOT EXISTS idx_chat_escalations_status_id ON ${db_schema}.chat_escalations (status_id);
