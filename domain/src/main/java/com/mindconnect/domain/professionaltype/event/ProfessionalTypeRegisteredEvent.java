package com.mindconnect.domain.professionaltype.event;

import java.time.LocalDateTime;

import com.mindconnect.domain.common.event.DomainEvent;
import com.mindconnect.domain.professionaltype.model.valueobject.ProfessionalTypeId;

/**
 * Evento de dominio: se registró un registro de professional_types.
 */
public record ProfessionalTypeRegisteredEvent(
        ProfessionalTypeId id,
        LocalDateTime occurredOn
) implements DomainEvent {
}
