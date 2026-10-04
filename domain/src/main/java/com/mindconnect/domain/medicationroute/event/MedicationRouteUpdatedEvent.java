package com.mindconnect.domain.medicationroute.event;

import java.time.LocalDateTime;

import com.mindconnect.domain.common.event.DomainEvent;
import com.mindconnect.domain.medicationroute.model.valueobject.MedicationRouteId;

/**
 * Evento de dominio: se actualizó un registro de medication_routes.
 */
public record MedicationRouteUpdatedEvent(
        MedicationRouteId id,
        LocalDateTime occurredOn
) implements DomainEvent {
}
