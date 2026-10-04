package com.mindconnect.domain.clinicalrecord.event;

import java.time.LocalDateTime;

import com.mindconnect.domain.common.event.DomainEvent;
import com.mindconnect.domain.clinicalrecord.model.valueobject.ClinicalRecordId;

/**
 * Evento de dominio: se registró un registro de clinical_records.
 */
public record ClinicalRecordRegisteredEvent(
        ClinicalRecordId id,
        LocalDateTime occurredOn
) implements DomainEvent {
}
