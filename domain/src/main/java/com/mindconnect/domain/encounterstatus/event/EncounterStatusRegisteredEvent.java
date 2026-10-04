package com.mindconnect.domain.encounterstatus.event;

import java.time.LocalDateTime;

import com.mindconnect.domain.common.event.DomainEvent;
import com.mindconnect.domain.encounterstatus.model.valueobject.EncounterStatusId;

/**
 * Evento de dominio: se registró un registro de encounter_statuses.
 */
public record EncounterStatusRegisteredEvent(
        EncounterStatusId id,
        LocalDateTime occurredOn
) implements DomainEvent {
}
