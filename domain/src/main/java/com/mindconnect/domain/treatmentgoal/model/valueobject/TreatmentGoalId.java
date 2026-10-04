package com.mindconnect.domain.treatmentgoal.model.valueobject;

import java.util.Objects;
import java.util.UUID;

/**
 * Identificador de TreatmentGoal. Envuelve el UUID para que no se confunda con el id de otro agregado.
 */
public record TreatmentGoalId(UUID value) {

    public TreatmentGoalId {
        Objects.requireNonNull(value, "id must not be null");
    }

    public static TreatmentGoalId generate() {
        return new TreatmentGoalId(UUID.randomUUID());
    }
}
