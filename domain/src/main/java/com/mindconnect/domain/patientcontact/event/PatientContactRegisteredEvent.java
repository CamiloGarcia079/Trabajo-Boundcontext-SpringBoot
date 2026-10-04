package com.mindconnect.domain.patientcontact.event;

import java.time.LocalDateTime;

import com.mindconnect.domain.common.event.DomainEvent;
import com.mindconnect.domain.patientcontact.model.valueobject.PatientContactId;

/**
 * Evento de dominio: se registró un registro de patient_contacts.
 */
public record PatientContactRegisteredEvent(
        PatientContactId id,
        LocalDateTime occurredOn
) implements DomainEvent {
}
