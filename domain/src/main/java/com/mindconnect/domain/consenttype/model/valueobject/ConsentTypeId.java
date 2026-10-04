package com.mindconnect.domain.consenttype.model.valueobject;

import java.util.Objects;
import java.util.UUID;

/**
 * Identificador de ConsentType. Envuelve el UUID para que no se confunda con el id de otro agregado.
 */
public record ConsentTypeId(UUID value) {

    public ConsentTypeId {
        Objects.requireNonNull(value, "id must not be null");
    }

    public static ConsentTypeId generate() {
        return new ConsentTypeId(UUID.randomUUID());
    }
}
