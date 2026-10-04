-- Paso 3: crear las 52 tablas del diagrama, ya en el orden correcto.
-- Es el mismo contenido de las migraciones V1 a V52 de Flyway, con el esquema
-- mindconnect_schema escrito directamente en lugar del placeholder ${db_schema}.
-- Sirve para correrlo a mano en pgAdmin o psql. Si la aplicación ya ejecutó Flyway, no hace falta.

-- 01. countries
CREATE TABLE IF NOT EXISTS mindconnect_schema.countries (
    id UUID PRIMARY KEY,
    name_country VARCHAR(50) NOT NULL,
    code_country VARCHAR(10) NOT NULL,
    description VARCHAR(100) NOT NULL,
    is_active BOOLEAN NOT NULL,
    telephone_prefix VARCHAR(5) NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITHOUT TIME ZONE NOT NULL
);

-- 02. state_regions
CREATE TABLE IF NOT EXISTS mindconnect_schema.state_regions (
    id UUID PRIMARY KEY,
    name_region VARCHAR(50) NOT NULL,
    code_region VARCHAR(10) NOT NULL,
    description VARCHAR(100) NOT NULL,
    is_active BOOLEAN NOT NULL,
    country_id UUID NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT fk_state_regions_country_id FOREIGN KEY (country_id) REFERENCES mindconnect_schema.countries (id)
);

CREATE INDEX IF NOT EXISTS idx_state_regions_country_id ON mindconnect_schema.state_regions (country_id);

-- 03. city_municipalities
CREATE TABLE IF NOT EXISTS mindconnect_schema.city_municipalities (
    id UUID PRIMARY KEY,
    name_city VARCHAR(50) NOT NULL,
    code_city VARCHAR(10) NOT NULL,
    description VARCHAR(100) NOT NULL,
    is_active BOOLEAN NOT NULL,
    region_id UUID NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT fk_city_municipalities_region_id FOREIGN KEY (region_id) REFERENCES mindconnect_schema.state_regions (id)
);

CREATE INDEX IF NOT EXISTS idx_city_municipalities_region_id ON mindconnect_schema.city_municipalities (region_id);

-- 04. document_types
CREATE TABLE IF NOT EXISTS mindconnect_schema.document_types (
    id UUID PRIMARY KEY,
    code VARCHAR(20) NOT NULL,
    name VARCHAR(50) NOT NULL,
    active BOOLEAN NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT uq_document_types_code UNIQUE (code)
);

-- 05. genders
CREATE TABLE IF NOT EXISTS mindconnect_schema.genders (
    id UUID PRIMARY KEY,
    description VARCHAR(50) NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT uq_genders_description UNIQUE (description)
);

-- 06. relationship_types
CREATE TABLE IF NOT EXISTS mindconnect_schema.relationship_types (
    id UUID PRIMARY KEY,
    description VARCHAR(50) NOT NULL,
    CONSTRAINT uq_relationship_types_description UNIQUE (description)
);

-- 07. professional_types
CREATE TABLE IF NOT EXISTS mindconnect_schema.professional_types (
    id UUID PRIMARY KEY,
    name VARCHAR(40) NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT uq_professional_types_name UNIQUE (name)
);

-- 08. studies
CREATE TABLE IF NOT EXISTS mindconnect_schema.studies (
    id UUID PRIMARY KEY,
    name VARCHAR(40) NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITHOUT TIME ZONE NOT NULL
);

-- 09. clinical_record_statuses
CREATE TABLE IF NOT EXISTS mindconnect_schema.clinical_record_statuses (
    id UUID PRIMARY KEY,
    code VARCHAR(20) NOT NULL,
    name VARCHAR(50) NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT uq_clinical_record_statuses_code UNIQUE (code),
    CONSTRAINT uq_clinical_record_statuses_name UNIQUE (name)
);

-- 10. encounter_types
CREATE TABLE IF NOT EXISTS mindconnect_schema.encounter_types (
    id UUID PRIMARY KEY,
    code VARCHAR(20) NOT NULL,
    name VARCHAR(50) NOT NULL,
    active BOOLEAN NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT uq_encounter_types_code UNIQUE (code),
    CONSTRAINT uq_encounter_types_name UNIQUE (name)
);

-- 11. encounter_modalities
CREATE TABLE IF NOT EXISTS mindconnect_schema.encounter_modalities (
    id UUID PRIMARY KEY,
    code VARCHAR(20) NOT NULL,
    name VARCHAR(50) NOT NULL,
    active BOOLEAN NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT uq_encounter_modalities_code UNIQUE (code)
);

-- 12. encounter_statuses
CREATE TABLE IF NOT EXISTS mindconnect_schema.encounter_statuses (
    id UUID PRIMARY KEY,
    code VARCHAR(20) NOT NULL,
    name VARCHAR(50) NOT NULL,
    active BOOLEAN NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT uq_encounter_statuses_code UNIQUE (code),
    CONSTRAINT uq_encounter_statuses_name UNIQUE (name)
);

-- 13. risk_levels
CREATE TABLE IF NOT EXISTS mindconnect_schema.risk_levels (
    id UUID PRIMARY KEY,
    code VARCHAR(20) NOT NULL,
    name VARCHAR(50) NOT NULL,
    active BOOLEAN NOT NULL,
    severity INTEGER NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT uq_risk_levels_code UNIQUE (code)
);

-- 14. treatment_statuses
CREATE TABLE IF NOT EXISTS mindconnect_schema.treatment_statuses (
    id UUID PRIMARY KEY,
    code VARCHAR(20) NOT NULL,
    name VARCHAR(50) NOT NULL,
    active BOOLEAN NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT uq_treatment_statuses_code UNIQUE (code),
    CONSTRAINT uq_treatment_statuses_name UNIQUE (name)
);

-- 15. treatment_goal_statuses
CREATE TABLE IF NOT EXISTS mindconnect_schema.treatment_goal_statuses (
    id UUID PRIMARY KEY,
    code VARCHAR(20) NOT NULL,
    name VARCHAR(50) NOT NULL,
    active BOOLEAN NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT uq_treatment_goal_statuses_code UNIQUE (code),
    CONSTRAINT uq_treatment_goal_statuses_name UNIQUE (name)
);

-- 16. medication_routes
CREATE TABLE IF NOT EXISTS mindconnect_schema.medication_routes (
    id UUID PRIMARY KEY,
    code VARCHAR(20) NOT NULL,
    name VARCHAR(50) NOT NULL,
    active BOOLEAN NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT uq_medication_routes_code UNIQUE (code)
);

-- 17. assessment_types
CREATE TABLE IF NOT EXISTS mindconnect_schema.assessment_types (
    id UUID PRIMARY KEY,
    code VARCHAR(20) NOT NULL,
    name VARCHAR(50) NOT NULL,
    active BOOLEAN NOT NULL,
    description TEXT NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT uq_assessment_types_code UNIQUE (code)
);

-- 18. consent_types
CREATE TABLE IF NOT EXISTS mindconnect_schema.consent_types (
    id UUID PRIMARY KEY,
    code VARCHAR(20) NOT NULL,
    name VARCHAR(50) NOT NULL,
    active BOOLEAN NOT NULL,
    description TEXT NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT uq_consent_types_code UNIQUE (code)
);

-- 19. diagnostic_systems
CREATE TABLE IF NOT EXISTS mindconnect_schema.diagnostic_systems (
    id UUID PRIMARY KEY,
    code VARCHAR(20) NOT NULL,
    name VARCHAR(50) NOT NULL,
    active BOOLEAN NOT NULL,
    version VARCHAR(20) NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT uq_diagnostic_systems_code UNIQUE (code)
);

-- 20. professionals
CREATE TABLE IF NOT EXISTS mindconnect_schema.professionals (
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
    CONSTRAINT fk_professionals_document_type_id FOREIGN KEY (document_type_id) REFERENCES mindconnect_schema.document_types (id),
    CONSTRAINT fk_professionals_professional_type FOREIGN KEY (professional_type) REFERENCES mindconnect_schema.professional_types (id),
    CONSTRAINT fk_professionals_city_id FOREIGN KEY (city_id) REFERENCES mindconnect_schema.city_municipalities (id)
);

CREATE INDEX IF NOT EXISTS idx_professionals_document_type_id ON mindconnect_schema.professionals (document_type_id);
CREATE INDEX IF NOT EXISTS idx_professionals_professional_type ON mindconnect_schema.professionals (professional_type);
CREATE INDEX IF NOT EXISTS idx_professionals_city_id ON mindconnect_schema.professionals (city_id);

-- 21. professional_studies
CREATE TABLE IF NOT EXISTS mindconnect_schema.professional_studies (
    id UUID PRIMARY KEY,
    study_id UUID NOT NULL,
    professional_id UUID NOT NULL,
    title VARCHAR(100) NOT NULL,
    university VARCHAR(100) NOT NULL,
    is_valid BOOLEAN NOT NULL,
    resolution_number VARCHAR(60),
    country_id UUID NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT fk_professional_studies_study_id FOREIGN KEY (study_id) REFERENCES mindconnect_schema.studies (id),
    CONSTRAINT fk_professional_studies_professional_id FOREIGN KEY (professional_id) REFERENCES mindconnect_schema.professionals (id),
    CONSTRAINT fk_professional_studies_country_id FOREIGN KEY (country_id) REFERENCES mindconnect_schema.countries (id)
);

CREATE INDEX IF NOT EXISTS idx_professional_studies_study_id ON mindconnect_schema.professional_studies (study_id);
CREATE INDEX IF NOT EXISTS idx_professional_studies_professional_id ON mindconnect_schema.professional_studies (professional_id);
CREATE INDEX IF NOT EXISTS idx_professional_studies_country_id ON mindconnect_schema.professional_studies (country_id);

-- 22. contacts
CREATE TABLE IF NOT EXISTS mindconnect_schema.contacts (
    id UUID PRIMARY KEY,
    full_name VARCHAR(200) NOT NULL,
    email VARCHAR(150) NOT NULL,
    notes TEXT NOT NULL,
    city_id UUID NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    created_by UUID NOT NULL,
    updated_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_by UUID,
    CONSTRAINT fk_contacts_city_id FOREIGN KEY (city_id) REFERENCES mindconnect_schema.city_municipalities (id),
    CONSTRAINT fk_contacts_created_by FOREIGN KEY (created_by) REFERENCES mindconnect_schema.professionals (id),
    CONSTRAINT fk_contacts_updated_by FOREIGN KEY (updated_by) REFERENCES mindconnect_schema.professionals (id)
);

CREATE INDEX IF NOT EXISTS idx_contacts_city_id ON mindconnect_schema.contacts (city_id);
CREATE INDEX IF NOT EXISTS idx_contacts_created_by ON mindconnect_schema.contacts (created_by);
CREATE INDEX IF NOT EXISTS idx_contacts_updated_by ON mindconnect_schema.contacts (updated_by);

-- 23. phone_contacts
CREATE TABLE IF NOT EXISTS mindconnect_schema.phone_contacts (
    id UUID PRIMARY KEY,
    contact_id UUID NOT NULL,
    phone VARCHAR(30),
    notes TEXT NOT NULL,
    CONSTRAINT fk_phone_contacts_contact_id FOREIGN KEY (contact_id) REFERENCES mindconnect_schema.contacts (id)
);

CREATE INDEX IF NOT EXISTS idx_phone_contacts_contact_id ON mindconnect_schema.phone_contacts (contact_id);

-- 24. email_contacts
CREATE TABLE IF NOT EXISTS mindconnect_schema.email_contacts (
    id UUID PRIMARY KEY,
    contact_id UUID NOT NULL,
    email VARCHAR(150) NOT NULL,
    notes TEXT NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT uq_email_contacts_email UNIQUE (email),
    CONSTRAINT fk_email_contacts_contact_id FOREIGN KEY (contact_id) REFERENCES mindconnect_schema.contacts (id)
);

CREATE INDEX IF NOT EXISTS idx_email_contacts_contact_id ON mindconnect_schema.email_contacts (contact_id);

-- 25. patients
CREATE TABLE IF NOT EXISTS mindconnect_schema.patients (
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
    CONSTRAINT fk_patients_document_type_id FOREIGN KEY (document_type_id) REFERENCES mindconnect_schema.document_types (id),
    CONSTRAINT fk_patients_biological_sex_id FOREIGN KEY (biological_sex_id) REFERENCES mindconnect_schema.genders (id),
    CONSTRAINT fk_patients_gender_identity FOREIGN KEY (gender_identity) REFERENCES mindconnect_schema.genders (id),
    CONSTRAINT fk_patients_created_by FOREIGN KEY (created_by) REFERENCES mindconnect_schema.professionals (id),
    CONSTRAINT fk_patients_updated_by FOREIGN KEY (updated_by) REFERENCES mindconnect_schema.professionals (id),
    CONSTRAINT fk_patients_city_id FOREIGN KEY (city_id) REFERENCES mindconnect_schema.city_municipalities (id)
);

CREATE INDEX IF NOT EXISTS idx_patients_document_type_id ON mindconnect_schema.patients (document_type_id);
CREATE INDEX IF NOT EXISTS idx_patients_biological_sex_id ON mindconnect_schema.patients (biological_sex_id);
CREATE INDEX IF NOT EXISTS idx_patients_gender_identity ON mindconnect_schema.patients (gender_identity);
CREATE INDEX IF NOT EXISTS idx_patients_created_by ON mindconnect_schema.patients (created_by);
CREATE INDEX IF NOT EXISTS idx_patients_updated_by ON mindconnect_schema.patients (updated_by);
CREATE INDEX IF NOT EXISTS idx_patients_city_id ON mindconnect_schema.patients (city_id);

-- 26. patient_allergies
CREATE TABLE IF NOT EXISTS mindconnect_schema.patient_allergies (
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
    CONSTRAINT fk_patient_allergies_patient_id FOREIGN KEY (patient_id) REFERENCES mindconnect_schema.patients (id),
    CONSTRAINT fk_patient_allergies_recorded_by FOREIGN KEY (recorded_by) REFERENCES mindconnect_schema.professionals (id)
);

CREATE INDEX IF NOT EXISTS idx_patient_allergies_patient_id ON mindconnect_schema.patient_allergies (patient_id);
CREATE INDEX IF NOT EXISTS idx_patient_allergies_recorded_by ON mindconnect_schema.patient_allergies (recorded_by);

-- 27. patient_contacts
CREATE TABLE IF NOT EXISTS mindconnect_schema.patient_contacts (
    id UUID PRIMARY KEY,
    contact_id UUID NOT NULL,
    patient_id UUID NOT NULL,
    is_primary_contact BOOLEAN NOT NULL,
    is_emergency_contact BOOLEAN NOT NULL,
    relationship_type_id UUID NOT NULL,
    CONSTRAINT fk_patient_contacts_contact_id FOREIGN KEY (contact_id) REFERENCES mindconnect_schema.contacts (id),
    CONSTRAINT fk_patient_contacts_patient_id FOREIGN KEY (patient_id) REFERENCES mindconnect_schema.patients (id),
    CONSTRAINT fk_patient_contacts_relationship_type_id FOREIGN KEY (relationship_type_id) REFERENCES mindconnect_schema.relationship_types (id)
);

CREATE INDEX IF NOT EXISTS idx_patient_contacts_contact_id ON mindconnect_schema.patient_contacts (contact_id);
CREATE INDEX IF NOT EXISTS idx_patient_contacts_patient_id ON mindconnect_schema.patient_contacts (patient_id);
CREATE INDEX IF NOT EXISTS idx_patient_contacts_relationship_type_id ON mindconnect_schema.patient_contacts (relationship_type_id);

-- 28. clinical_records
CREATE TABLE IF NOT EXISTS mindconnect_schema.clinical_records (
    id UUID PRIMARY KEY,
    patient_id UUID NOT NULL,
    creation_date TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    record_number VARCHAR(50) NOT NULL,
    opened_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    closed_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    status_id UUID NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    created_by UUID NOT NULL,
    CONSTRAINT fk_clinical_records_patient_id FOREIGN KEY (patient_id) REFERENCES mindconnect_schema.patients (id),
    CONSTRAINT fk_clinical_records_status_id FOREIGN KEY (status_id) REFERENCES mindconnect_schema.clinical_record_statuses (id),
    CONSTRAINT fk_clinical_records_created_by FOREIGN KEY (created_by) REFERENCES mindconnect_schema.professionals (id)
);

CREATE INDEX IF NOT EXISTS idx_clinical_records_patient_id ON mindconnect_schema.clinical_records (patient_id);
CREATE INDEX IF NOT EXISTS idx_clinical_records_status_id ON mindconnect_schema.clinical_records (status_id);
CREATE INDEX IF NOT EXISTS idx_clinical_records_created_by ON mindconnect_schema.clinical_records (created_by);

-- 29. encounters
CREATE TABLE IF NOT EXISTS mindconnect_schema.encounters (
    id UUID PRIMARY KEY,
    clinical_record_id UUID NOT NULL,
    professional_id UUID NOT NULL,
    encounter_type_id UUID NOT NULL,
    started_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    ended_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    reason_for_visit TEXT NOT NULL,
    current_condition TEXT NOT NULL,
    modality_id UUID NOT NULL,
    status_id UUID NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    created_by UUID NOT NULL,
    updated_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_by UUID NOT NULL,
    CONSTRAINT fk_encounters_clinical_record_id FOREIGN KEY (clinical_record_id) REFERENCES mindconnect_schema.clinical_records (id),
    CONSTRAINT fk_encounters_professional_id FOREIGN KEY (professional_id) REFERENCES mindconnect_schema.professionals (id),
    CONSTRAINT fk_encounters_encounter_type_id FOREIGN KEY (encounter_type_id) REFERENCES mindconnect_schema.encounter_types (id),
    CONSTRAINT fk_encounters_modality_id FOREIGN KEY (modality_id) REFERENCES mindconnect_schema.encounter_modalities (id),
    CONSTRAINT fk_encounters_status_id FOREIGN KEY (status_id) REFERENCES mindconnect_schema.encounter_statuses (id),
    CONSTRAINT fk_encounters_created_by FOREIGN KEY (created_by) REFERENCES mindconnect_schema.professionals (id),
    CONSTRAINT fk_encounters_updated_by FOREIGN KEY (updated_by) REFERENCES mindconnect_schema.professionals (id)
);

CREATE INDEX IF NOT EXISTS idx_encounters_clinical_record_id ON mindconnect_schema.encounters (clinical_record_id);
CREATE INDEX IF NOT EXISTS idx_encounters_professional_id ON mindconnect_schema.encounters (professional_id);
CREATE INDEX IF NOT EXISTS idx_encounters_encounter_type_id ON mindconnect_schema.encounters (encounter_type_id);
CREATE INDEX IF NOT EXISTS idx_encounters_modality_id ON mindconnect_schema.encounters (modality_id);
CREATE INDEX IF NOT EXISTS idx_encounters_status_id ON mindconnect_schema.encounters (status_id);
CREATE INDEX IF NOT EXISTS idx_encounters_created_by ON mindconnect_schema.encounters (created_by);
CREATE INDEX IF NOT EXISTS idx_encounters_updated_by ON mindconnect_schema.encounters (updated_by);

-- 30. clinical_notes
CREATE TABLE IF NOT EXISTS mindconnect_schema.clinical_notes (
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
    CONSTRAINT fk_clinical_notes_encounter_id FOREIGN KEY (encounter_id) REFERENCES mindconnect_schema.encounters (id),
    CONSTRAINT fk_clinical_notes_professional_id FOREIGN KEY (professional_id) REFERENCES mindconnect_schema.professionals (id)
);

CREATE INDEX IF NOT EXISTS idx_clinical_notes_encounter_id ON mindconnect_schema.clinical_notes (encounter_id);
CREATE INDEX IF NOT EXISTS idx_clinical_notes_professional_id ON mindconnect_schema.clinical_notes (professional_id);

-- 31. mental_status_exams
CREATE TABLE IF NOT EXISTS mindconnect_schema.mental_status_exams (
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
    CONSTRAINT fk_mental_status_exams_encounter_id FOREIGN KEY (encounter_id) REFERENCES mindconnect_schema.encounters (id),
    CONSTRAINT fk_mental_status_exams_created_by FOREIGN KEY (created_by) REFERENCES mindconnect_schema.professionals (id)
);

CREATE INDEX IF NOT EXISTS idx_mental_status_exams_encounter_id ON mindconnect_schema.mental_status_exams (encounter_id);
CREATE INDEX IF NOT EXISTS idx_mental_status_exams_created_by ON mindconnect_schema.mental_status_exams (created_by);

-- 32. risk_assessments
CREATE TABLE IF NOT EXISTS mindconnect_schema.risk_assessments (
    id UUID PRIMARY KEY,
    encounter_id UUID NOT NULL,
    risk_level_id UUID NOT NULL,
    suicidal_ideation BOOLEAN NOT NULL,
    suicide_plan BOOLEAN NOT NULL,
    suicide_intent BOOLEAN NOT NULL,
    self_harm BOOLEAN NOT NULL,
    harm_to_others BOOLEAN NOT NULL,
    risk_factors TEXT NOT NULL,
    protective_factors TEXT NOT NULL,
    clinical_actions TEXT NOT NULL,
    observations TEXT NOT NULL,
    assessed_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    assessed_by UUID NOT NULL,
    CONSTRAINT fk_risk_assessments_encounter_id FOREIGN KEY (encounter_id) REFERENCES mindconnect_schema.encounters (id),
    CONSTRAINT fk_risk_assessments_risk_level_id FOREIGN KEY (risk_level_id) REFERENCES mindconnect_schema.risk_levels (id),
    CONSTRAINT fk_risk_assessments_assessed_by FOREIGN KEY (assessed_by) REFERENCES mindconnect_schema.professionals (id)
);

CREATE INDEX IF NOT EXISTS idx_risk_assessments_encounter_id ON mindconnect_schema.risk_assessments (encounter_id);
CREATE INDEX IF NOT EXISTS idx_risk_assessments_risk_level_id ON mindconnect_schema.risk_assessments (risk_level_id);
CREATE INDEX IF NOT EXISTS idx_risk_assessments_assessed_by ON mindconnect_schema.risk_assessments (assessed_by);

-- 33. treatment_plans
CREATE TABLE IF NOT EXISTS mindconnect_schema.treatment_plans (
    id UUID PRIMARY KEY,
    encounter_id UUID NOT NULL,
    professional_id UUID NOT NULL,
    title VARCHAR(200) NOT NULL,
    description TEXT NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    treatment_status_id UUID NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT fk_treatment_plans_encounter_id FOREIGN KEY (encounter_id) REFERENCES mindconnect_schema.encounters (id),
    CONSTRAINT fk_treatment_plans_professional_id FOREIGN KEY (professional_id) REFERENCES mindconnect_schema.professionals (id),
    CONSTRAINT fk_treatment_plans_treatment_status_id FOREIGN KEY (treatment_status_id) REFERENCES mindconnect_schema.treatment_statuses (id)
);

CREATE INDEX IF NOT EXISTS idx_treatment_plans_encounter_id ON mindconnect_schema.treatment_plans (encounter_id);
CREATE INDEX IF NOT EXISTS idx_treatment_plans_professional_id ON mindconnect_schema.treatment_plans (professional_id);
CREATE INDEX IF NOT EXISTS idx_treatment_plans_treatment_status_id ON mindconnect_schema.treatment_plans (treatment_status_id);

-- 34. treatment_goals
CREATE TABLE IF NOT EXISTS mindconnect_schema.treatment_goals (
    id UUID PRIMARY KEY,
    treatment_plan_id UUID NOT NULL,
    description TEXT NOT NULL,
    target_date DATE NOT NULL,
    completed_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    notes TEXT NOT NULL,
    treatment_goal_id UUID NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT fk_treatment_goals_treatment_plan_id FOREIGN KEY (treatment_plan_id) REFERENCES mindconnect_schema.treatment_plans (id),
    CONSTRAINT fk_treatment_goals_treatment_goal_id FOREIGN KEY (treatment_goal_id) REFERENCES mindconnect_schema.treatment_goal_statuses (id)
);

CREATE INDEX IF NOT EXISTS idx_treatment_goals_treatment_plan_id ON mindconnect_schema.treatment_goals (treatment_plan_id);
CREATE INDEX IF NOT EXISTS idx_treatment_goals_treatment_goal_id ON mindconnect_schema.treatment_goals (treatment_goal_id);

-- 35. priorities
CREATE TABLE IF NOT EXISTS mindconnect_schema.priorities (
    id UUID PRIMARY KEY,
    name_priority VARCHAR(50) NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITHOUT TIME ZONE NOT NULL
);

-- 36. conversations_statuses
CREATE TABLE IF NOT EXISTS mindconnect_schema.conversations_statuses (
    id UUID PRIMARY KEY,
    name_status VARCHAR(50) NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITHOUT TIME ZONE NOT NULL
);

-- 37. sender_types
CREATE TABLE IF NOT EXISTS mindconnect_schema.sender_types (
    id UUID PRIMARY KEY,
    name_type VARCHAR(50) NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITHOUT TIME ZONE NOT NULL
);

-- 38. message_types
CREATE TABLE IF NOT EXISTS mindconnect_schema.message_types (
    id UUID PRIMARY KEY,
    name_type VARCHAR(50) NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITHOUT TIME ZONE NOT NULL
);

-- 39. ai_runs_statuses
CREATE TABLE IF NOT EXISTS mindconnect_schema.ai_runs_statuses (
    id UUID PRIMARY KEY,
    name_status VARCHAR(50) NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITHOUT TIME ZONE NOT NULL
);

-- 40. escalations_statuses
CREATE TABLE IF NOT EXISTS mindconnect_schema.escalations_statuses (
    id UUID PRIMARY KEY,
    name_status VARCHAR(50) NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITHOUT TIME ZONE NOT NULL
);

-- 41. provider_models_ai
CREATE TABLE IF NOT EXISTS mindconnect_schema.provider_models_ai (
    id UUID PRIMARY KEY,
    name_provider_ai VARCHAR(100) NOT NULL,
    razon_social VARCHAR(150) NOT NULL,
    sitio_web TEXT NOT NULL,
    is_active BOOLEAN NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITHOUT TIME ZONE NOT NULL
);

-- 42. ai_models
CREATE TABLE IF NOT EXISTS mindconnect_schema.ai_models (
    id UUID PRIMARY KEY,
    provider_model_id UUID NOT NULL,
    name_model VARCHAR(100) NOT NULL,
    model_key VARCHAR(120) NOT NULL,
    input_token_price DECIMAL(12,8) NOT NULL,
    output_token_price DECIMAL(12,8) NOT NULL,
    max_tokens INTEGER NOT NULL,
    context_window INTEGER NOT NULL,
    is_active BOOLEAN NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT fk_ai_models_provider_model_id FOREIGN KEY (provider_model_id) REFERENCES mindconnect_schema.provider_models_ai (id)
);

CREATE INDEX IF NOT EXISTS idx_ai_models_provider_model_id ON mindconnect_schema.ai_models (provider_model_id);

-- 43. chat_conversations
CREATE TABLE IF NOT EXISTS mindconnect_schema.chat_conversations (
    id UUID PRIMARY KEY,
    conversation_status_id UUID NOT NULL,
    priority_id UUID NOT NULL,
    last_message_at TIMESTAMP WITHOUT TIME ZONE,
    closed BOOLEAN,
    closed_at TIMESTAMP WITHOUT TIME ZONE,
    closed_by UUID,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT fk_chat_conversations_conversation_status_id FOREIGN KEY (conversation_status_id) REFERENCES mindconnect_schema.conversations_statuses (id),
    CONSTRAINT fk_chat_conversations_priority_id FOREIGN KEY (priority_id) REFERENCES mindconnect_schema.priorities (id)
);

CREATE INDEX IF NOT EXISTS idx_chat_conversations_conversation_status_id ON mindconnect_schema.chat_conversations (conversation_status_id);
CREATE INDEX IF NOT EXISTS idx_chat_conversations_priority_id ON mindconnect_schema.chat_conversations (priority_id);

-- 44. chat_participants
CREATE TABLE IF NOT EXISTS mindconnect_schema.chat_participants (
    id UUID PRIMARY KEY,
    conversation_id UUID NOT NULL,
    participant_type_id UUID NOT NULL,
    patient_id UUID,
    professional_id UUID,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT fk_chat_participants_conversation_id FOREIGN KEY (conversation_id) REFERENCES mindconnect_schema.chat_conversations (id),
    CONSTRAINT fk_chat_participants_participant_type_id FOREIGN KEY (participant_type_id) REFERENCES mindconnect_schema.sender_types (id),
    CONSTRAINT fk_chat_participants_patient_id FOREIGN KEY (patient_id) REFERENCES mindconnect_schema.patients (id),
    CONSTRAINT fk_chat_participants_professional_id FOREIGN KEY (professional_id) REFERENCES mindconnect_schema.professionals (id)
);

CREATE INDEX IF NOT EXISTS idx_chat_participants_conversation_id ON mindconnect_schema.chat_participants (conversation_id);
CREATE INDEX IF NOT EXISTS idx_chat_participants_participant_type_id ON mindconnect_schema.chat_participants (participant_type_id);
CREATE INDEX IF NOT EXISTS idx_chat_participants_patient_id ON mindconnect_schema.chat_participants (patient_id);
CREATE INDEX IF NOT EXISTS idx_chat_participants_professional_id ON mindconnect_schema.chat_participants (professional_id);

-- 45. chat_messages
CREATE TABLE IF NOT EXISTS mindconnect_schema.chat_messages (
    id UUID PRIMARY KEY,
    conversation_id UUID NOT NULL,
    message_type_id UUID NOT NULL,
    participant_id UUID NOT NULL,
    content JSONB NOT NULL,
    metadata JSONB NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT fk_chat_messages_conversation_id FOREIGN KEY (conversation_id) REFERENCES mindconnect_schema.chat_conversations (id),
    CONSTRAINT fk_chat_messages_message_type_id FOREIGN KEY (message_type_id) REFERENCES mindconnect_schema.message_types (id),
    CONSTRAINT fk_chat_messages_participant_id FOREIGN KEY (participant_id) REFERENCES mindconnect_schema.chat_participants (id)
);

CREATE INDEX IF NOT EXISTS idx_chat_messages_conversation_id ON mindconnect_schema.chat_messages (conversation_id);
CREATE INDEX IF NOT EXISTS idx_chat_messages_message_type_id ON mindconnect_schema.chat_messages (message_type_id);
CREATE INDEX IF NOT EXISTS idx_chat_messages_participant_id ON mindconnect_schema.chat_messages (participant_id);

-- 46. chat_conversation_ai_settings
CREATE TABLE IF NOT EXISTS mindconnect_schema.chat_conversation_ai_settings (
    id UUID PRIMARY KEY,
    conversation_id UUID NOT NULL,
    ai_enabled BOOLEAN NOT NULL,
    default_model_id UUID NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT fk_chat_conversation_ai_settings_conversation_id FOREIGN KEY (conversation_id) REFERENCES mindconnect_schema.chat_conversations (id),
    CONSTRAINT fk_chat_conversation_ai_settings_default_model_id FOREIGN KEY (default_model_id) REFERENCES mindconnect_schema.ai_models (id)
);

CREATE INDEX IF NOT EXISTS idx_chat_conversation_ai_settings_conversation_id ON mindconnect_schema.chat_conversation_ai_settings (conversation_id);
CREATE INDEX IF NOT EXISTS idx_chat_conversation_ai_settings_default_model_id ON mindconnect_schema.chat_conversation_ai_settings (default_model_id);

-- 47. chat_ai_runs
CREATE TABLE IF NOT EXISTS mindconnect_schema.chat_ai_runs (
    id UUID PRIMARY KEY,
    conversation_id UUID NOT NULL,
    message_id UUID NOT NULL,
    model_id UUID NOT NULL,
    ai_run_status_id UUID NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT fk_chat_ai_runs_conversation_id FOREIGN KEY (conversation_id) REFERENCES mindconnect_schema.chat_conversations (id),
    CONSTRAINT fk_chat_ai_runs_message_id FOREIGN KEY (message_id) REFERENCES mindconnect_schema.chat_messages (id),
    CONSTRAINT fk_chat_ai_runs_model_id FOREIGN KEY (model_id) REFERENCES mindconnect_schema.ai_models (id),
    CONSTRAINT fk_chat_ai_runs_ai_run_status_id FOREIGN KEY (ai_run_status_id) REFERENCES mindconnect_schema.ai_runs_statuses (id)
);

CREATE INDEX IF NOT EXISTS idx_chat_ai_runs_conversation_id ON mindconnect_schema.chat_ai_runs (conversation_id);
CREATE INDEX IF NOT EXISTS idx_chat_ai_runs_message_id ON mindconnect_schema.chat_ai_runs (message_id);
CREATE INDEX IF NOT EXISTS idx_chat_ai_runs_model_id ON mindconnect_schema.chat_ai_runs (model_id);
CREATE INDEX IF NOT EXISTS idx_chat_ai_runs_ai_run_status_id ON mindconnect_schema.chat_ai_runs (ai_run_status_id);

-- 48. chat_ai_run_errors
CREATE TABLE IF NOT EXISTS mindconnect_schema.chat_ai_run_errors (
    id UUID PRIMARY KEY,
    ai_run_id UUID NOT NULL,
    error_message TEXT NOT NULL,
    error_code VARCHAR(80) NOT NULL,
    provider_error_id VARCHAR(120) NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT fk_chat_ai_run_errors_ai_run_id FOREIGN KEY (ai_run_id) REFERENCES mindconnect_schema.chat_ai_runs (id)
);

CREATE INDEX IF NOT EXISTS idx_chat_ai_run_errors_ai_run_id ON mindconnect_schema.chat_ai_run_errors (ai_run_id);

-- 49. chat_ai_run_metrics
CREATE TABLE IF NOT EXISTS mindconnect_schema.chat_ai_run_metrics (
    id UUID PRIMARY KEY,
    ai_run_id UUID NOT NULL,
    prompt_tokens INTEGER NOT NULL,
    completion_tokens INTEGER NOT NULL,
    total_tokens INTEGER NOT NULL,
    cost DECIMAL(10,6) NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT fk_chat_ai_run_metrics_ai_run_id FOREIGN KEY (ai_run_id) REFERENCES mindconnect_schema.chat_ai_runs (id)
);

CREATE INDEX IF NOT EXISTS idx_chat_ai_run_metrics_ai_run_id ON mindconnect_schema.chat_ai_run_metrics (ai_run_id);

-- 50. chat_escalations
CREATE TABLE IF NOT EXISTS mindconnect_schema.chat_escalations (
    id UUID PRIMARY KEY,
    conversation_id UUID NOT NULL,
    status_id UUID NOT NULL,
    from_ai BOOLEAN NOT NULL,
    reason TEXT NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT fk_chat_escalations_conversation_id FOREIGN KEY (conversation_id) REFERENCES mindconnect_schema.chat_conversations (id),
    CONSTRAINT fk_chat_escalations_status_id FOREIGN KEY (status_id) REFERENCES mindconnect_schema.escalations_statuses (id)
);

CREATE INDEX IF NOT EXISTS idx_chat_escalations_conversation_id ON mindconnect_schema.chat_escalations (conversation_id);
CREATE INDEX IF NOT EXISTS idx_chat_escalations_status_id ON mindconnect_schema.chat_escalations (status_id);

-- 51. chat_escalation_assignments
CREATE TABLE IF NOT EXISTS mindconnect_schema.chat_escalation_assignments (
    id UUID PRIMARY KEY,
    escalation_id UUID NOT NULL,
    professional_id UUID NOT NULL,
    assigned_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT fk_chat_escalation_assignments_escalation_id FOREIGN KEY (escalation_id) REFERENCES mindconnect_schema.chat_escalations (id),
    CONSTRAINT fk_chat_escalation_assignments_professional_id FOREIGN KEY (professional_id) REFERENCES mindconnect_schema.professionals (id)
);

CREATE INDEX IF NOT EXISTS idx_chat_escalation_assignments_escalation_id ON mindconnect_schema.chat_escalation_assignments (escalation_id);
CREATE INDEX IF NOT EXISTS idx_chat_escalation_assignments_professional_id ON mindconnect_schema.chat_escalation_assignments (professional_id);

-- 52. chat_escalation_status_history
CREATE TABLE IF NOT EXISTS mindconnect_schema.chat_escalation_status_history (
    id UUID PRIMARY KEY,
    escalation_id UUID NOT NULL,
    escalation_status_id UUID NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    changed_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT fk_chat_escalation_status_history_escalation_id FOREIGN KEY (escalation_id) REFERENCES mindconnect_schema.chat_escalations (id),
    CONSTRAINT fk_chat_escalation_status_history_escalation_status_id FOREIGN KEY (escalation_status_id) REFERENCES mindconnect_schema.escalations_statuses (id)
);

CREATE INDEX IF NOT EXISTS idx_chat_escalation_status_history_escalation_id ON mindconnect_schema.chat_escalation_status_history (escalation_id);
CREATE INDEX IF NOT EXISTS idx_chat_escalation_status_history_escalation_status_id ON mindconnect_schema.chat_escalation_status_history (escalation_status_id);
