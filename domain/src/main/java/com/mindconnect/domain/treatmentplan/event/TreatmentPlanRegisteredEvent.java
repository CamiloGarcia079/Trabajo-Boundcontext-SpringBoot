package com.mindconnect.domain.treatmentplan.event;

import java.time.LocalDateTime;

import com.mindconnect.domain.common.event.DomainEvent;
import com.mindconnect.domain.treatmentplan.model.valueobject.TreatmentPlanId;

/**
 * Evento de dominio: se registró un registro de treatment_plans.
 */
public record TreatmentPlanRegisteredEvent(
        TreatmentPlanId id,
        LocalDateTime occurredOn
) implements DomainEvent {
}
