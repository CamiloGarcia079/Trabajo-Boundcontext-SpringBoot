CREATE TABLE IF NOT EXISTS ${db_schema}.patients (
    id UUID PRIMARY KEY,
    document_type_id UUID NOT NULL,
    document_number VARCHAR(30) NOT NULL,
    first_name VARCHAR(50) NOT NULL,
    middle_name VARCHAR(50),
    last_name VARCHAR(50) NOT NULL,
    second_last_name VARCHAR(50),
    birth_date DATE NOT NULL,
    biological_sex_id UUID NOT NULL,
    gender_identity UUID NOT NULL,
    email VARCHAR(150) NOT NULL,
    phone VARCHAR(30) NOT NULL,
    address VARCHAR(250) NOT NULL,
    active BOOLEAN NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    created_by UUID,
    updated_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_by UUID,
    city_id UUID NOT NULL,
    CONSTRAINT uq_patients_email UNIQUE (email),
    CONSTRAINT fk_patients_document_type_id FOREIGN KEY (document_type_id) REFERENCES ${db_schema}.document_types (id),
    CONSTRAINT fk_patients_biological_sex_id FOREIGN KEY (biological_sex_id) REFERENCES ${db_schema}.genders (id),
    CONSTRAINT fk_patients_gender_identity FOREIGN KEY (gender_identity) REFERENCES ${db_schema}.genders (id),
    CONSTRAINT fk_patients_created_by FOREIGN KEY (created_by) REFERENCES ${db_schema}.professionals (id),
    CONSTRAINT fk_patients_updated_by FOREIGN KEY (updated_by) REFERENCES ${db_schema}.professionals (id),
    CONSTRAINT fk_patients_city_id FOREIGN KEY (city_id) REFERENCES ${db_schema}.city_municipalities (id)
);

CREATE INDEX IF NOT EXISTS idx_patients_document_type_id ON ${db_schema}.patients (document_type_id);
CREATE INDEX IF NOT EXISTS idx_patients_biological_sex_id ON ${db_schema}.patients (biological_sex_id);
CREATE INDEX IF NOT EXISTS idx_patients_gender_identity ON ${db_schema}.patients (gender_identity);
CREATE INDEX IF NOT EXISTS idx_patients_created_by ON ${db_schema}.patients (created_by);
CREATE INDEX IF NOT EXISTS idx_patients_updated_by ON ${db_schema}.patients (updated_by);
CREATE INDEX IF NOT EXISTS idx_patients_city_id ON ${db_schema}.patients (city_id);
