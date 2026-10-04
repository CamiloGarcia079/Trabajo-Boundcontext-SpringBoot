CREATE TABLE IF NOT EXISTS ${db_schema}.city_municipalities (
    id UUID PRIMARY KEY,
    name_city VARCHAR(50) NOT NULL,
    code_city VARCHAR(10) NOT NULL,
    description VARCHAR(100) NOT NULL,
    is_active BOOLEAN NOT NULL,
    region_id UUID NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT fk_city_municipalities_region_id FOREIGN KEY (region_id) REFERENCES ${db_schema}.state_regions (id)
);

CREATE INDEX IF NOT EXISTS idx_city_municipalities_region_id ON ${db_schema}.city_municipalities (region_id);
