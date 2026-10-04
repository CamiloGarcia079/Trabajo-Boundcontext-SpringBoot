package com.mindconnect.domain.phonecontact.model.valueobject;

import java.util.Objects;
import java.util.UUID;

/**
 * Identificador de PhoneContact. Envuelve el UUID para que no se confunda con el id de otro agregado.
 */
public record PhoneContactId(UUID value) {

    public PhoneContactId {
        Objects.requireNonNull(value, "id must not be null");
    }

    public static PhoneContactId generate() {
        return new PhoneContactId(UUID.randomUUID());
    }
}
