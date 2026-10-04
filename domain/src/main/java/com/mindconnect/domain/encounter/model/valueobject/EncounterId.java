package com.mindconnect.domain.encounter.model.valueobject;

import java.util.Objects;
import java.util.UUID;

/**
 * Identificador de Encounter. Envuelve el UUID para que no se confunda con el id de otro agregado.
 */
public record EncounterId(UUID value) {

    public EncounterId {
        Objects.requireNonNull(value, "id must not be null");
    }

    public static EncounterId generate() {
        return new EncounterId(UUID.randomUUID());
    }
}
