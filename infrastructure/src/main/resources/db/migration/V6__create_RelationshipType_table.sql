CREATE TABLE IF NOT EXISTS ${db_schema}.relationship_types (
    id UUID PRIMARY KEY,
    description VARCHAR(50) NOT NULL,
    CONSTRAINT uq_relationship_types_description UNIQUE (description)
);
