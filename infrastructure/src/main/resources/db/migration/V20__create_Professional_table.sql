CREATE TABLE IF NOT EXISTS ${db_schema}.professionals (
    id UUID PRIMARY KEY,
    document_type_id UUID NOT NULL,
    document_number VARCHAR(30) NOT NULL,
    first_name VARCHAR(60) NOT NULL,
    last_name VARCHAR(60) NOT NULL,
    professional_type UUID NOT NULL,
    license_number VARCHAR(100) NOT NULL,
    active BOOLEAN NOT NULL,
    city_id UUID NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT uq_professionals_document_number UNIQUE (document_number),
    CONSTRAINT uq_professionals_license_number UNIQUE (license_number),
    CONSTRAINT fk_professionals_document_type_id FOREIGN KEY (document_type_id) REFERENCES ${db_schema}.document_types (id),
    CONSTRAINT fk_professionals_professional_type FOREIGN KEY (professional_type) REFERENCES ${db_schema}.professional_types (id),
    CONSTRAINT fk_professionals_city_id FOREIGN KEY (city_id) REFERENCES ${db_schema}.city_municipalities (id)
);

CREATE INDEX IF NOT EXISTS idx_professionals_document_type_id ON ${db_schema}.professionals (document_type_id);
CREATE INDEX IF NOT EXISTS idx_professionals_professional_type ON ${db_schema}.professionals (professional_type);
CREATE INDEX IF NOT EXISTS idx_professionals_city_id ON ${db_schema}.professionals (city_id);
