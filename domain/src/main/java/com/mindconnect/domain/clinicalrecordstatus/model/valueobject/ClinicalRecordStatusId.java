package com.mindconnect.domain.clinicalrecordstatus.model.valueobject;

import java.util.Objects;
import java.util.UUID;

/**
 * Identificador de ClinicalRecordStatus. Envuelve el UUID para que no se confunda con el id de otro agregado.
 */
public record ClinicalRecordStatusId(UUID value) {

    public ClinicalRecordStatusId {
        Objects.requireNonNull(value, "id must not be null");
    }

    public static ClinicalRecordStatusId generate() {
        return new ClinicalRecordStatusId(UUID.randomUUID());
    }
}
