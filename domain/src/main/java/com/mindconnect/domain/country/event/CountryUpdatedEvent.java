package com.mindconnect.domain.country.event;

import java.time.LocalDateTime;

import com.mindconnect.domain.common.event.DomainEvent;
import com.mindconnect.domain.country.model.valueobject.CountryId;

/**
 * Evento de dominio: se actualizó un registro de countries.
 */
public record CountryUpdatedEvent(
        CountryId id,
        LocalDateTime occurredOn
) implements DomainEvent {
}
