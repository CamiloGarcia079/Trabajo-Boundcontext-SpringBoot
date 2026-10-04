package com.mindconnect.domain.encountertype.model.valueobject;

import java.util.Objects;
import java.util.UUID;

/**
 * Identificador de EncounterType. Envuelve el UUID para que no se confunda con el id de otro agregado.
 */
public record EncounterTypeId(UUID value) {

    public EncounterTypeId {
        Objects.requireNonNull(value, "id must not be null");
    }

    public static EncounterTypeId generate() {
        return new EncounterTypeId(UUID.randomUUID());
    }
}
