package com.mindconnect.domain.assessmenttype.event;

import java.time.LocalDateTime;

import com.mindconnect.domain.common.event.DomainEvent;
import com.mindconnect.domain.assessmenttype.model.valueobject.AssessmentTypeId;

/**
 * Evento de dominio: se actualizó un registro de assessment_types.
 */
public record AssessmentTypeUpdatedEvent(
        AssessmentTypeId id,
        LocalDateTime occurredOn
) implements DomainEvent {
}
