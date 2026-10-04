package com.mindconnect.domain.clinicalnote.model.valueobject;

import java.util.Objects;
import java.util.UUID;

/**
 * Identificador de ClinicalNote. Envuelve el UUID para que no se confunda con el id de otro agregado.
 */
public record ClinicalNoteId(UUID value) {

    public ClinicalNoteId {
        Objects.requireNonNull(value, "id must not be null");
    }

    public static ClinicalNoteId generate() {
        return new ClinicalNoteId(UUID.randomUUID());
    }
}
