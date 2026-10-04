package com.mindconnect.domain.consenttype.event;

import java.time.LocalDateTime;

import com.mindconnect.domain.common.event.DomainEvent;
import com.mindconnect.domain.consenttype.model.valueobject.ConsentTypeId;

/**
 * Evento de dominio: se eliminó un registro de consent_types.
 */
public record ConsentTypeDeletedEvent(
        ConsentTypeId id,
        LocalDateTime occurredOn
) implements DomainEvent {
}
