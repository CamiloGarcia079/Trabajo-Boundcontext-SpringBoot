package com.mindconnect.domain.patient.model.valueobject;

import java.util.Objects;
import java.util.UUID;

/**
 * Identificador de Patient. Envuelve el UUID para que no se confunda con el id de otro agregado.
 */
public record PatientId(UUID value) {

    public PatientId {
        Objects.requireNonNull(value, "id must not be null");
    }

    public static PatientId generate() {
        return new PatientId(UUID.randomUUID());
    }
}
