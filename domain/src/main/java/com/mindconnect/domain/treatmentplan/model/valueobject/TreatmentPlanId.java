package com.mindconnect.domain.treatmentplan.model.valueobject;

import java.util.Objects;
import java.util.UUID;

/**
 * Identificador de TreatmentPlan. Envuelve el UUID para que no se confunda con el id de otro agregado.
 */
public record TreatmentPlanId(UUID value) {

    public TreatmentPlanId {
        Objects.requireNonNull(value, "id must not be null");
    }

    public static TreatmentPlanId generate() {
        return new TreatmentPlanId(UUID.randomUUID());
    }
}
