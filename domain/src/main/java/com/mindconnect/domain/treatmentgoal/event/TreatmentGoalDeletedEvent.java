package com.mindconnect.domain.treatmentgoal.event;

import java.time.LocalDateTime;

import com.mindconnect.domain.common.event.DomainEvent;
import com.mindconnect.domain.treatmentgoal.model.valueobject.TreatmentGoalId;

/**
 * Evento de dominio: se eliminó un registro de treatment_goals.
 */
public record TreatmentGoalDeletedEvent(
        TreatmentGoalId id,
        LocalDateTime occurredOn
) implements DomainEvent {
}
