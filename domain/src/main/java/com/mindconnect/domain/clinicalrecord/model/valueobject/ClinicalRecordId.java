package com.mindconnect.domain.clinicalrecord.model.valueobject;

import java.util.Objects;
import java.util.UUID;

/**
 * Identificador de ClinicalRecord. Envuelve el UUID para que no se confunda con el id de otro agregado.
 */
public record ClinicalRecordId(UUID value) {

    public ClinicalRecordId {
        Objects.requireNonNull(value, "id must not be null");
    }

    public static ClinicalRecordId generate() {
        return new ClinicalRecordId(UUID.randomUUID());
    }
}
