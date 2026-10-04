package com.mindconnect.domain.citymunicipality.event;

import java.time.LocalDateTime;

import com.mindconnect.domain.common.event.DomainEvent;
import com.mindconnect.domain.citymunicipality.model.valueobject.CityMunicipalityId;

/**
 * Evento de dominio: se eliminó un registro de city_municipalities.
 */
public record CityMunicipalityDeletedEvent(
        CityMunicipalityId id,
        LocalDateTime occurredOn
) implements DomainEvent {
}
