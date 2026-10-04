package com.mindconnect.domain.emailcontact.event;

import java.time.LocalDateTime;

import com.mindconnect.domain.common.event.DomainEvent;
import com.mindconnect.domain.emailcontact.model.valueobject.EmailContactId;

/**
 * Evento de dominio: se eliminó un registro de email_contacts.
 */
public record EmailContactDeletedEvent(
        EmailContactId id,
        LocalDateTime occurredOn
) implements DomainEvent {
}
