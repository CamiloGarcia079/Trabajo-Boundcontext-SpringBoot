package com.mindconnect.domain.medicationroute.model.valueobject;

import java.util.Objects;
import java.util.UUID;

/**
 * Identificador de MedicationRoute. Envuelve el UUID para que no se confunda con el id de otro agregado.
 */
public record MedicationRouteId(UUID value) {

    public MedicationRouteId {
        Objects.requireNonNull(value, "id must not be null");
    }

    public static MedicationRouteId generate() {
        return new MedicationRouteId(UUID.randomUUID());
    }
}
