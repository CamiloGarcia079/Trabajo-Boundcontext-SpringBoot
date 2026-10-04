package com.mindconnect.domain.medicationroute.event;

import java.time.LocalDateTime;

import com.mindconnect.domain.common.event.DomainEvent;
import com.mindconnect.domain.medicationroute.model.valueobject.MedicationRouteId;

/**
 * Evento de dominio: se eliminó un registro de medication_routes.
 */
public record MedicationRouteDeletedEvent(
        MedicationRouteId id,
        LocalDateTime occurredOn
) implements DomainEvent {
}
