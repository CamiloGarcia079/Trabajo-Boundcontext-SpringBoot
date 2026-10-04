package com.mindconnect.domain.encountertype.event;

import java.time.LocalDateTime;

import com.mindconnect.domain.common.event.DomainEvent;
import com.mindconnect.domain.encountertype.model.valueobject.EncounterTypeId;

/**
 * Evento de dominio: se eliminó un registro de encounter_types.
 */
public record EncounterTypeDeletedEvent(
        EncounterTypeId id,
        LocalDateTime occurredOn
) implements DomainEvent {
}
