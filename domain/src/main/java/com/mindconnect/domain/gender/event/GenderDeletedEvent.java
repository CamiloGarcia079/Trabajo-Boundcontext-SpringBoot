package com.mindconnect.domain.gender.event;

import java.time.LocalDateTime;

import com.mindconnect.domain.common.event.DomainEvent;
import com.mindconnect.domain.gender.model.valueobject.GenderId;

/**
 * Evento de dominio: se eliminó un registro de genders.
 */
public record GenderDeletedEvent(
        GenderId id,
        LocalDateTime occurredOn
) implements DomainEvent {
}
