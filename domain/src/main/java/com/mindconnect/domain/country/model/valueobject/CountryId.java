package com.mindconnect.domain.country.model.valueobject;

import java.util.Objects;
import java.util.UUID;

/**
 * Identificador de Country. Envuelve el UUID para que no se confunda con el id de otro agregado.
 */
public record CountryId(UUID value) {

    public CountryId {
        Objects.requireNonNull(value, "id must not be null");
    }

    public static CountryId generate() {
        return new CountryId(UUID.randomUUID());
    }
}
