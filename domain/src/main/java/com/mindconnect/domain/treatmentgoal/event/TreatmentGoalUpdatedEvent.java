package com.mindconnect.domain.treatmentgoal.event;

import java.time.LocalDateTime;

import com.mindconnect.domain.common.event.DomainEvent;
import com.mindconnect.domain.treatmentgoal.model.valueobject.TreatmentGoalId;

/**
 * Evento de dominio: se actualizó un registro de treatment_goals.
 */
public record TreatmentGoalUpdatedEvent(
        TreatmentGoalId id,
        LocalDateTime occurredOn
) implements DomainEvent {
}
