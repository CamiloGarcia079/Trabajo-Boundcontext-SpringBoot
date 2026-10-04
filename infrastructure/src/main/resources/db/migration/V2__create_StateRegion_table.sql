CREATE TABLE IF NOT EXISTS ${db_schema}.state_regions (
    id UUID PRIMARY KEY,
    name_region VARCHAR(50) NOT NULL,
    code_region VARCHAR(10) NOT NULL,
    description VARCHAR(100) NOT NULL,
    is_active BOOLEAN NOT NULL,
    country_id UUID NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT fk_state_regions_country_id FOREIGN KEY (country_id) REFERENCES ${db_schema}.countries (id)
);

CREATE INDEX IF NOT EXISTS idx_state_regions_country_id ON ${db_schema}.state_regions (country_id);
