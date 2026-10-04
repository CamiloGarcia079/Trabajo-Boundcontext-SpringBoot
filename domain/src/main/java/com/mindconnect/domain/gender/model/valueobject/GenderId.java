package com.mindconnect.domain.gender.model.valueobject;

import java.util.Objects;
import java.util.UUID;

/**
 * Identificador de Gender. Envuelve el UUID para que no se confunda con el id de otro agregado.
 */
public record GenderId(UUID value) {

    public GenderId {
        Objects.requireNonNull(value, "id must not be null");
    }

    public static GenderId generate() {
        return new GenderId(UUID.randomUUID());
    }
}
