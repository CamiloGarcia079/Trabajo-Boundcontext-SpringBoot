CREATE TABLE IF NOT EXISTS ${db_schema}.email_contacts (
    id UUID PRIMARY KEY,
    contact_id UUID NOT NULL,
    email VARCHAR(150) NOT NULL,
    notes TEXT NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT uq_email_contacts_email UNIQUE (email),
    CONSTRAINT fk_email_contacts_contact_id FOREIGN KEY (contact_id) REFERENCES ${db_schema}.contacts (id)
);

CREATE INDEX IF NOT EXISTS idx_email_contacts_contact_id ON ${db_schema}.email_contacts (contact_id);
