package com.mindconnect.domain.patient.event;

import java.time.LocalDateTime;

import com.mindconnect.domain.common.event.DomainEvent;
import com.mindconnect.domain.patient.model.valueobject.PatientId;

/**
 * Evento de dominio: se actualizó un registro de patients.
 */
public record PatientUpdatedEvent(
        PatientId id,
        LocalDateTime occurredOn
) implements DomainEvent {
}
