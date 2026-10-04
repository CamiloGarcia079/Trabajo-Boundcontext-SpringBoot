package com.mindconnect.domain.contact.event;

import java.time.LocalDateTime;

import com.mindconnect.domain.common.event.DomainEvent;
import com.mindconnect.domain.contact.model.valueobject.ContactId;

/**
 * Evento de dominio: se actualizó un registro de contacts.
 */
public record ContactUpdatedEvent(
        ContactId id,
        LocalDateTime occurredOn
) implements DomainEvent {
}
