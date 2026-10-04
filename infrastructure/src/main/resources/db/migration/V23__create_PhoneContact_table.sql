CREATE TABLE IF NOT EXISTS ${db_schema}.phone_contacts (
    id UUID PRIMARY KEY,
    contact_id UUID NOT NULL,
    phone VARCHAR(30),
    notes TEXT NOT NULL,
    CONSTRAINT fk_phone_contacts_contact_id FOREIGN KEY (contact_id) REFERENCES ${db_schema}.contacts (id)
);

CREATE INDEX IF NOT EXISTS idx_phone_contacts_contact_id ON ${db_schema}.phone_contacts (contact_id);
