package com.mindconnect.domain.treatmentgoalstatus.event;

import java.time.LocalDateTime;

import com.mindconnect.domain.common.event.DomainEvent;
import com.mindconnect.domain.treatmentgoalstatus.model.valueobject.TreatmentGoalStatusId;

/**
 * Evento de dominio: se registró un registro de treatment_goal_statuses.
 */
public record TreatmentGoalStatusRegisteredEvent(
        TreatmentGoalStatusId id,
        LocalDateTime occurredOn
) implements DomainEvent {
}
