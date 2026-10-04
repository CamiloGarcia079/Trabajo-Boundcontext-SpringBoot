package com.mindconnect.domain.professional.event;

import java.time.LocalDateTime;

import com.mindconnect.domain.common.event.DomainEvent;
import com.mindconnect.domain.professional.model.valueobject.ProfessionalId;

/**
 * Evento de dominio: se registró un registro de professionals.
 */
public record ProfessionalRegisteredEvent(
        ProfessionalId id,
        LocalDateTime occurredOn
) implements DomainEvent {
}
