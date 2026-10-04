package com.mindconnect.domain.relationshiptype.model.valueobject;

import java.util.Objects;
import java.util.UUID;

/**
 * Identificador de RelationshipType. Envuelve el UUID para que no se confunda con el id de otro agregado.
 */
public record RelationshipTypeId(UUID value) {

    public RelationshipTypeId {
        Objects.requireNonNull(value, "id must not be null");
    }

    public static RelationshipTypeId generate() {
        return new RelationshipTypeId(UUID.randomUUID());
    }
}
