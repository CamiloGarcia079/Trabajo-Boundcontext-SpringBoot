package com.mindconnect.domain.encounter.event;

import java.time.LocalDateTime;

import com.mindconnect.domain.common.event.DomainEvent;
import com.mindconnect.domain.encounter.model.valueobject.EncounterId;

/**
 * Evento de dominio: se actualizó un registro de encounters.
 */
public record EncounterUpdatedEvent(
        EncounterId id,
        LocalDateTime occurredOn
) implements DomainEvent {
}
