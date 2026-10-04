package com.mindconnect.domain.medicationroute.event;

import java.time.LocalDateTime;

import com.mindconnect.domain.common.event.DomainEvent;
import com.mindconnect.domain.medicationroute.model.valueobject.MedicationRouteId;

/**
 * Evento de dominio: se registró un registro de medication_routes.
 */
public record MedicationRouteRegisteredEvent(
        MedicationRouteId id,
        LocalDateTime occurredOn
) implements DomainEvent {
}
