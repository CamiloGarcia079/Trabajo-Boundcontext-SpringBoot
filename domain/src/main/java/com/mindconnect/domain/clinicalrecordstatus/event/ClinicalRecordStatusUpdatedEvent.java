package com.mindconnect.domain.clinicalrecordstatus.event;

import java.time.LocalDateTime;

import com.mindconnect.domain.common.event.DomainEvent;
import com.mindconnect.domain.clinicalrecordstatus.model.valueobject.ClinicalRecordStatusId;

/**
 * Evento de dominio: se actualizó un registro de clinical_record_statuses.
 */
public record ClinicalRecordStatusUpdatedEvent(
        ClinicalRecordStatusId id,
        LocalDateTime occurredOn
) implements DomainEvent {
}
