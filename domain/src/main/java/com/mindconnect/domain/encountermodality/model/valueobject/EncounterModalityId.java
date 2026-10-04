package com.mindconnect.domain.encountermodality.model.valueobject;

import java.util.Objects;
import java.util.UUID;

/**
 * Identificador de EncounterModality. Envuelve el UUID para que no se confunda con el id de otro agregado.
 */
public record EncounterModalityId(UUID value) {

    public EncounterModalityId {
        Objects.requireNonNull(value, "id must not be null");
    }

    public static EncounterModalityId generate() {
        return new EncounterModalityId(UUID.randomUUID());
    }
}
