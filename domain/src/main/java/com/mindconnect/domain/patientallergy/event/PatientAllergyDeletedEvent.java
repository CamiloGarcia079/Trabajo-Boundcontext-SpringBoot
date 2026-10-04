package com.mindconnect.domain.patientallergy.event;

import java.time.LocalDateTime;

import com.mindconnect.domain.common.event.DomainEvent;
import com.mindconnect.domain.patientallergy.model.valueobject.PatientAllergyId;

/**
 * Evento de dominio: se eliminó un registro de patient_allergies.
 */
public record PatientAllergyDeletedEvent(
        PatientAllergyId id,
        LocalDateTime occurredOn
) implements DomainEvent {
}
