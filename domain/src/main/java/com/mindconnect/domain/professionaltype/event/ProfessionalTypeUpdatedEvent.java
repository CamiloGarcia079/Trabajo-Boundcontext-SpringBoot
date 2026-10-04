package com.mindconnect.domain.professionaltype.event;

import java.time.LocalDateTime;

import com.mindconnect.domain.common.event.DomainEvent;
import com.mindconnect.domain.professionaltype.model.valueobject.ProfessionalTypeId;

/**
 * Evento de dominio: se actualizó un registro de professional_types.
 */
public record ProfessionalTypeUpdatedEvent(
        ProfessionalTypeId id,
        LocalDateTime occurredOn
) implements DomainEvent {
}
