package com.mindconnect.domain.encounterstatus.model.valueobject;

import java.util.Objects;
import java.util.UUID;

/**
 * Identificador de EncounterStatus. Envuelve el UUID para que no se confunda con el id de otro agregado.
 */
public record EncounterStatusId(UUID value) {

    public EncounterStatusId {
        Objects.requireNonNull(value, "id must not be null");
    }

    public static EncounterStatusId generate() {
        return new EncounterStatusId(UUID.randomUUID());
    }
}
