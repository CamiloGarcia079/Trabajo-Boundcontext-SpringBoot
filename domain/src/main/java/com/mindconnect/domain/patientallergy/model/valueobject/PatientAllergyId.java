package com.mindconnect.domain.patientallergy.model.valueobject;

import java.util.Objects;
import java.util.UUID;

/**
 * Identificador de PatientAllergy. Envuelve el UUID para que no se confunda con el id de otro agregado.
 */
public record PatientAllergyId(UUID value) {

    public PatientAllergyId {
        Objects.requireNonNull(value, "id must not be null");
    }

    public static PatientAllergyId generate() {
        return new PatientAllergyId(UUID.randomUUID());
    }
}
