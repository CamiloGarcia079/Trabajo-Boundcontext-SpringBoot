package com.mindconnect.domain.contact.event;

import java.time.LocalDateTime;

import com.mindconnect.domain.common.event.DomainEvent;
import com.mindconnect.domain.contact.model.valueobject.ContactId;

/**
 * Evento de dominio: se eliminó un registro de contacts.
 */
public record ContactDeletedEvent(
        ContactId id,
        LocalDateTime occurredOn
) implements DomainEvent {
}
