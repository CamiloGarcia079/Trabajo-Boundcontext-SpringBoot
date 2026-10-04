package com.mindconnect.domain.phonecontact.event;

import java.time.LocalDateTime;

import com.mindconnect.domain.common.event.DomainEvent;
import com.mindconnect.domain.phonecontact.model.valueobject.PhoneContactId;

/**
 * Evento de dominio: se registró un registro de phone_contacts.
 */
public record PhoneContactRegisteredEvent(
        PhoneContactId id,
        LocalDateTime occurredOn
) implements DomainEvent {
}
