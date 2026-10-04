package com.mindconnect.domain.riskassessment.event;

import java.time.LocalDateTime;

import com.mindconnect.domain.common.event.DomainEvent;
import com.mindconnect.domain.riskassessment.model.valueobject.RiskAssessmentId;

/**
 * Evento de dominio: se actualizó un registro de risk_assessments.
 */
public record RiskAssessmentUpdatedEvent(
        RiskAssessmentId id,
        LocalDateTime occurredOn
) implements DomainEvent {
}
