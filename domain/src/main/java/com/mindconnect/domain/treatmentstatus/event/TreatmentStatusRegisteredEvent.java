package com.mindconnect.domain.treatmentstatus.event;

import java.time.LocalDateTime;

import com.mindconnect.domain.common.event.DomainEvent;
import com.mindconnect.domain.treatmentstatus.model.valueobject.TreatmentStatusId;

/**
 * Evento de dominio: se registró un registro de treatment_statuses.
 */
public record TreatmentStatusRegisteredEvent(
        TreatmentStatusId id,
        LocalDateTime occurredOn
) implements DomainEvent {
}
