CREATE TABLE IF NOT EXISTS ${db_schema}.ai_runs_statuses (
    id UUID PRIMARY KEY,
    name_status VARCHAR(50) NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITHOUT TIME ZONE NOT NULL
);
