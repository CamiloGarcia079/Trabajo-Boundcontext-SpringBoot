CREATE TABLE IF NOT EXISTS ${db_schema}.clinical_notes (
    id UUID PRIMARY KEY,
    encounter_id UUID NOT NULL,
    professional_id UUID NOT NULL,
    subjective TEXT NOT NULL,
    objective TEXT NOT NULL,
    assessment TEXT NOT NULL,
    plan TEXT NOT NULL,
    additional_notes TEXT NOT NULL,
    signed_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT fk_clinical_notes_encounter_id FOREIGN KEY (encounter_id) REFERENCES ${db_schema}.encounters (id),
    CONSTRAINT fk_clinical_notes_professional_id FOREIGN KEY (professional_id) REFERENCES ${db_schema}.professionals (id)
);

CREATE INDEX IF NOT EXISTS idx_clinical_notes_encounter_id ON ${db_schema}.clinical_notes (encounter_id);
CREATE INDEX IF NOT EXISTS idx_clinical_notes_professional_id ON ${db_schema}.clinical_notes (professional_id);
