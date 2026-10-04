package com.mindconnect.domain.contact.event;

import java.time.LocalDateTime;

import com.mindconnect.domain.common.event.DomainEvent;
import com.mindconnect.domain.contact.model.valueobject.ContactId;

/**
 * Evento de dominio: se registró un registro de contacts.
 */
public record ContactRegisteredEvent(
        ContactId id,
        LocalDateTime occurredOn
) implements DomainEvent {
}
