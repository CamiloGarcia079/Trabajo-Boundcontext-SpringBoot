CREATE TABLE IF NOT EXISTS ${db_schema}.encounter_statuses (
    id UUID PRIMARY KEY,
    code VARCHAR(20) NOT NULL,
    name VARCHAR(50) NOT NULL,
    active BOOLEAN NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT uq_encounter_statuses_code UNIQUE (code),
    CONSTRAINT uq_encounter_statuses_name UNIQUE (name)
);
