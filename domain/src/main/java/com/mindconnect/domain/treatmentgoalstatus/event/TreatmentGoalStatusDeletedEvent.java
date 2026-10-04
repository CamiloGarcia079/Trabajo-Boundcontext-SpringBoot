package com.mindconnect.domain.treatmentgoalstatus.event;

import java.time.LocalDateTime;

import com.mindconnect.domain.common.event.DomainEvent;
import com.mindconnect.domain.treatmentgoalstatus.model.valueobject.TreatmentGoalStatusId;

/**
 * Evento de dominio: se eliminó un registro de treatment_goal_statuses.
 */
public record TreatmentGoalStatusDeletedEvent(
        TreatmentGoalStatusId id,
        LocalDateTime occurredOn
) implements DomainEvent {
}
