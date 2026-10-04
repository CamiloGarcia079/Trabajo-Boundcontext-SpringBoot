package com.mindconnect.domain.diagnosticsystem.event;

import java.time.LocalDateTime;

import com.mindconnect.domain.common.event.DomainEvent;
import com.mindconnect.domain.diagnosticsystem.model.valueobject.DiagnosticSystemId;

/**
 * Evento de dominio: se registró un registro de diagnostic_systems.
 */
public record DiagnosticSystemRegisteredEvent(
        DiagnosticSystemId id,
        LocalDateTime occurredOn
) implements DomainEvent {
}
