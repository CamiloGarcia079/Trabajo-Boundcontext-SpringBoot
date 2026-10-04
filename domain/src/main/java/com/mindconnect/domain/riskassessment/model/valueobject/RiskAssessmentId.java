package com.mindconnect.domain.riskassessment.model.valueobject;

import java.util.Objects;
import java.util.UUID;

/**
 * Identificador de RiskAssessment. Envuelve el UUID para que no se confunda con el id de otro agregado.
 */
public record RiskAssessmentId(UUID value) {

    public RiskAssessmentId {
        Objects.requireNonNull(value, "id must not be null");
    }

    public static RiskAssessmentId generate() {
        return new RiskAssessmentId(UUID.randomUUID());
    }
}
