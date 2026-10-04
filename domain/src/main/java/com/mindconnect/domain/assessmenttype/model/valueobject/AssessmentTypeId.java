package com.mindconnect.domain.assessmenttype.model.valueobject;

import java.util.Objects;
import java.util.UUID;

/**
 * Identificador de AssessmentType. Envuelve el UUID para que no se confunda con el id de otro agregado.
 */
public record AssessmentTypeId(UUID value) {

    public AssessmentTypeId {
        Objects.requireNonNull(value, "id must not be null");
    }

    public static AssessmentTypeId generate() {
        return new AssessmentTypeId(UUID.randomUUID());
    }
}
