CREATE TABLE IF NOT EXISTS ${db_schema}.patient_allergies (
    id UUID PRIMARY KEY,
    patient_id UUID NOT NULL,
    substance VARCHAR(200) NOT NULL,
    reaction TEXT,
    severity VARCHAR(20) NOT NULL,
    active BOOLEAN NOT NULL,
    recorded_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    recorded_by UUID NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT fk_patient_allergies_patient_id FOREIGN KEY (patient_id) REFERENCES ${db_schema}.patients (id),
    CONSTRAINT fk_patient_allergies_recorded_by FOREIGN KEY (recorded_by) REFERENCES ${db_schema}.professionals (id)
);

CREATE INDEX IF NOT EXISTS idx_patient_allergies_patient_id ON ${db_schema}.patient_allergies (patient_id);
CREATE INDEX IF NOT EXISTS idx_patient_allergies_recorded_by ON ${db_schema}.patient_allergies (recorded_by);
