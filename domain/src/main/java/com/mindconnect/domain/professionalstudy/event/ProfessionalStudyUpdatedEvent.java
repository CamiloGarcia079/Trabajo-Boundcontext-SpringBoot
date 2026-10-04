package com.mindconnect.domain.professionalstudy.event;

import java.time.LocalDateTime;

import com.mindconnect.domain.common.event.DomainEvent;
import com.mindconnect.domain.professionalstudy.model.valueobject.ProfessionalStudyId;

/**
 * Evento de dominio: se actualizó un registro de professional_studies.
 */
public record ProfessionalStudyUpdatedEvent(
        ProfessionalStudyId id,
        LocalDateTime occurredOn
) implements DomainEvent {
}
