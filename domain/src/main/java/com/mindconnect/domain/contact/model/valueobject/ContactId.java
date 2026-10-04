package com.mindconnect.domain.contact.model.valueobject;

import java.util.Objects;
import java.util.UUID;

/**
 * Identificador de Contact. Envuelve el UUID para que no se confunda con el id de otro agregado.
 */
public record ContactId(UUID value) {

    public ContactId {
        Objects.requireNonNull(value, "id must not be null");
    }

    public static ContactId generate() {
        return new ContactId(UUID.randomUUID());
    }
}
