package com.mindconnect.domain.riskassessment.event;

import java.time.LocalDateTime;

import com.mindconnect.domain.common.event.DomainEvent;
import com.mindconnect.domain.riskassessment.model.valueobject.RiskAssessmentId;

/**
 * Evento de dominio: se registró un registro de risk_assessments.
 */
public record RiskAssessmentRegisteredEvent(
        RiskAssessmentId id,
        LocalDateTime occurredOn
) implements DomainEvent {
}
