CREATE TABLE IF NOT EXISTS ${db_schema}.genders (
    id UUID PRIMARY KEY,
    description VARCHAR(50) NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT uq_genders_description UNIQUE (description)
);
