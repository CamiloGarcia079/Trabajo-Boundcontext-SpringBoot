package com.mindconnect.domain.country.event;

import java.time.LocalDateTime;

import com.mindconnect.domain.common.event.DomainEvent;
import com.mindconnect.domain.country.model.valueobject.CountryId;

/**
 * Evento de dominio: se eliminó un registro de countries.
 */
public record CountryDeletedEvent(
        CountryId id,
        LocalDateTime occurredOn
) implements DomainEvent {
}
