CREATE TABLE IF NOT EXISTS ${db_schema}.contacts (
    id UUID PRIMARY KEY,
    full_name VARCHAR(200) NOT NULL,
    email VARCHAR(150) NOT NULL,
    notes TEXT NOT NULL,
    city_id UUID NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    created_by UUID NOT NULL,
    updated_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_by UUID,
    CONSTRAINT fk_contacts_city_id FOREIGN KEY (city_id) REFERENCES ${db_schema}.city_municipalities (id),
    CONSTRAINT fk_contacts_created_by FOREIGN KEY (created_by) REFERENCES ${db_schema}.professionals (id),
    CONSTRAINT fk_contacts_updated_by FOREIGN KEY (updated_by) REFERENCES ${db_schema}.professionals (id)
);

CREATE INDEX IF NOT EXISTS idx_contacts_city_id ON ${db_schema}.contacts (city_id);
CREATE INDEX IF NOT EXISTS idx_contacts_created_by ON ${db_schema}.contacts (created_by);
CREATE INDEX IF NOT EXISTS idx_contacts_updated_by ON ${db_schema}.contacts (updated_by);
