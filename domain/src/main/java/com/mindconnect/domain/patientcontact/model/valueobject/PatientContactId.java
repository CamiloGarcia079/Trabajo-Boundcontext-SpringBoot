package com.mindconnect.domain.patientcontact.model.valueobject;

import java.util.Objects;
import java.util.UUID;

/**
 * Identificador de PatientContact. Envuelve el UUID para que no se confunda con el id de otro agregado.
 */
public record PatientContactId(UUID value) {

    public PatientContactId {
        Objects.requireNonNull(value, "id must not be null");
    }

    public static PatientContactId generate() {
        return new PatientContactId(UUID.randomUUID());
    }
}
