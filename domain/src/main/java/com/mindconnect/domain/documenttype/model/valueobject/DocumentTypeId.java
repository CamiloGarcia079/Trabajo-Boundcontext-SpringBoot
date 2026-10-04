package com.mindconnect.domain.documenttype.model.valueobject;

import java.util.Objects;
import java.util.UUID;

/**
 * Identificador de DocumentType. Envuelve el UUID para que no se confunda con el id de otro agregado.
 */
public record DocumentTypeId(UUID value) {

    public DocumentTypeId {
        Objects.requireNonNull(value, "id must not be null");
    }

    public static DocumentTypeId generate() {
        return new DocumentTypeId(UUID.randomUUID());
    }
}
