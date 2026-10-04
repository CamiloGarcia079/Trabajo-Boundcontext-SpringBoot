package com.mindconnect.domain.patient.event;

import java.time.LocalDateTime;

import com.mindconnect.domain.common.event.DomainEvent;
import com.mindconnect.domain.patient.model.valueobject.PatientId;

/**
 * Evento de dominio: se registró un registro de patients.
 */
public record PatientRegisteredEvent(
        PatientId id,
        LocalDateTime occurredOn
) implements DomainEvent {
}
