package com.mindconnect.domain.clinicalnote.event;

import java.time.LocalDateTime;

import com.mindconnect.domain.common.event.DomainEvent;
import com.mindconnect.domain.clinicalnote.model.valueobject.ClinicalNoteId;

/**
 * Evento de dominio: se registró un registro de clinical_notes.
 */
public record ClinicalNoteRegisteredEvent(
        ClinicalNoteId id,
        LocalDateTime occurredOn
) implements DomainEvent {
}
