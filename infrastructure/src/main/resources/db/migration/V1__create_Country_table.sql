CREATE TABLE IF NOT EXISTS ${db_schema}.countries (
    id UUID PRIMARY KEY,
    name_country VARCHAR(50) NOT NULL,
    code_country VARCHAR(10) NOT NULL,
    description VARCHAR(100) NOT NULL,
    is_active BOOLEAN NOT NULL,
    telephone_prefix VARCHAR(5) NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITHOUT TIME ZONE NOT NULL
);
