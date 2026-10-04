package com.mindconnect.domain.emailcontact.model.valueobject;

import java.util.Objects;
import java.util.UUID;

/**
 * Identificador de EmailContact. Envuelve el UUID para que no se confunda con el id de otro agregado.
 */
public record EmailContactId(UUID value) {

    public EmailContactId {
        Objects.requireNonNull(value, "id must not be null");
    }

    public static EmailContactId generate() {
        return new EmailContactId(UUID.randomUUID());
    }
}
