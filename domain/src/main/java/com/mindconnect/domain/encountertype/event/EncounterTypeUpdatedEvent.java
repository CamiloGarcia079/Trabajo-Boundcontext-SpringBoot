package com.mindconnect.domain.encountertype.event;

import java.time.LocalDateTime;

import com.mindconnect.domain.common.event.DomainEvent;
import com.mindconnect.domain.encountertype.model.valueobject.EncounterTypeId;

/**
 * Evento de dominio: se actualizó un registro de encounter_types.
 */
public record EncounterTypeUpdatedEvent(
        EncounterTypeId id,
        LocalDateTime occurredOn
) implements DomainEvent {
}
