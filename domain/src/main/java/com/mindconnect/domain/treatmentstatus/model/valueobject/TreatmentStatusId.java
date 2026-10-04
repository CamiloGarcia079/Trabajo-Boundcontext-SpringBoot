package com.mindconnect.domain.treatmentstatus.model.valueobject;

import java.util.Objects;
import java.util.UUID;

/**
 * Identificador de TreatmentStatus. Envuelve el UUID para que no se confunda con el id de otro agregado.
 */
public record TreatmentStatusId(UUID value) {

    public TreatmentStatusId {
        Objects.requireNonNull(value, "id must not be null");
    }

    public static TreatmentStatusId generate() {
        return new TreatmentStatusId(UUID.randomUUID());
    }
}
