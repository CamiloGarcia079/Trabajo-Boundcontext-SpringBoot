CREATE TABLE IF NOT EXISTS ${db_schema}.mental_status_exams (
    id UUID PRIMARY KEY,
    encounter_id UUID NOT NULL,
    appearance TEXT NOT NULL,
    behavior TEXT NOT NULL,
    attitude TEXT NOT NULL,
    consciousness TEXT NOT NULL,
    orientation TEXT NOT NULL,
    attention TEXT NOT NULL,
    memory TEXT NOT NULL,
    speech TEXT NOT NULL,
    mood TEXT NOT NULL,
    affect TEXT NOT NULL,
    thought_process TEXT NOT NULL,
    thought_content TEXT NOT NULL,
    perception TEXT NOT NULL,
    judgment TEXT NOT NULL,
    insight TEXT NOT NULL,
    psychomotor_activity TEXT NOT NULL,
    observations TEXT NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    created_by UUID NOT NULL,
    CONSTRAINT fk_mental_status_exams_encounter_id FOREIGN KEY (encounter_id) REFERENCES ${db_schema}.encounters (id),
    CONSTRAINT fk_mental_status_exams_created_by FOREIGN KEY (created_by) REFERENCES ${db_schema}.professionals (id)
);

CREATE INDEX IF NOT EXISTS idx_mental_status_exams_encounter_id ON ${db_schema}.mental_status_exams (encounter_id);
CREATE INDEX IF NOT EXISTS idx_mental_status_exams_created_by ON ${db_schema}.mental_status_exams (created_by);
