package com.mindconnect.domain.emailcontact.event;

import java.time.LocalDateTime;

import com.mindconnect.domain.common.event.DomainEvent;
import com.mindconnect.domain.emailcontact.model.valueobject.EmailContactId;

/**
 * Evento de dominio: se registró un registro de email_contacts.
 */
public record EmailContactRegisteredEvent(
        EmailContactId id,
        LocalDateTime occurredOn
) implements DomainEvent {
}
