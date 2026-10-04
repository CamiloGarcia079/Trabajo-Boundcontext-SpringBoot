package com.mindconnect.domain.treatmentgoalstatus.model.valueobject;

import java.util.Objects;
import java.util.UUID;

/**
 * Identificador de TreatmentGoalStatus. Envuelve el UUID para que no se confunda con el id de otro agregado.
 */
public record TreatmentGoalStatusId(UUID value) {

    public TreatmentGoalStatusId {
        Objects.requireNonNull(value, "id must not be null");
    }

    public static TreatmentGoalStatusId generate() {
        return new TreatmentGoalStatusId(UUID.randomUUID());
    }
}
