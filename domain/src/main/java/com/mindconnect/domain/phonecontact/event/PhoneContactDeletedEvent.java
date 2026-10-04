package com.mindconnect.domain.phonecontact.event;

import java.time.LocalDateTime;

import com.mindconnect.domain.common.event.DomainEvent;
import com.mindconnect.domain.phonecontact.model.valueobject.PhoneContactId;

/**
 * Evento de dominio: se eliminó un registro de phone_contacts.
 */
public record PhoneContactDeletedEvent(
        PhoneContactId id,
        LocalDateTime occurredOn
) implements DomainEvent {
}
