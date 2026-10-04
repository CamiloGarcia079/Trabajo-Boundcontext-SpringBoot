package com.mindconnect.domain.professionalstudy.event;

import java.time.LocalDateTime;

import com.mindconnect.domain.common.event.DomainEvent;
import com.mindconnect.domain.professionalstudy.model.valueobject.ProfessionalStudyId;

/**
 * Evento de dominio: se registró un registro de professional_studies.
 */
public record ProfessionalStudyRegisteredEvent(
        ProfessionalStudyId id,
        LocalDateTime occurredOn
) implements DomainEvent {
}
