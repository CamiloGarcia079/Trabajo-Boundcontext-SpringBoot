package com.mindconnect.domain.gender.event;

import java.time.LocalDateTime;

import com.mindconnect.domain.common.event.DomainEvent;
import com.mindconnect.domain.gender.model.valueobject.GenderId;

/**
 * Evento de dominio: se registró un registro de genders.
 */
public record GenderRegisteredEvent(
        GenderId id,
        LocalDateTime occurredOn
) implements DomainEvent {
}
