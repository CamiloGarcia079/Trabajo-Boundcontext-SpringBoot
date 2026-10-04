package com.mindconnect.domain.encountermodality.event;

import java.time.LocalDateTime;

import com.mindconnect.domain.common.event.DomainEvent;
import com.mindconnect.domain.encountermodality.model.valueobject.EncounterModalityId;

/**
 * Evento de dominio: se eliminó un registro de encounter_modalities.
 */
public record EncounterModalityDeletedEvent(
        EncounterModalityId id,
        LocalDateTime occurredOn
) implements DomainEvent {
}
