package com.mindconnect.domain.stateregion.event;

import java.time.LocalDateTime;

import com.mindconnect.domain.common.event.DomainEvent;
import com.mindconnect.domain.stateregion.model.valueobject.StateRegionId;

/**
 * Evento de dominio: se actualizó un registro de state_regions.
 */
public record StateRegionUpdatedEvent(
        StateRegionId id,
        LocalDateTime occurredOn
) implements DomainEvent {
}
