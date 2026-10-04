package com.mindconnect.domain.common.event;

import java.time.LocalDateTime;

/**
 * Hecho importante que ocurrió en el dominio (por ejemplo, "se registró un paciente").
 */
public interface DomainEvent {
    LocalDateTime occurredOn();
}
