package com.mindconnect.application.riskassessment.command;

import java.time.LocalDateTime;
import java.util.UUID;

import com.mindconnect.domain.riskassessment.model.valueobject.RiskAssessmentId;

public record UpdateRiskAssessmentCommand(
        RiskAssessmentId id,
        UUID encounterId,
        UUID riskLevelId,
        boolean suicidalIdeation,
        boolean suicidePlan,
        boolean suicideIntent,
        boolean selfHarm,
        boolean harmToOthers,
        String riskFactors,
        String protectiveFactors,
        String clinicalActions,
        String observations,
        LocalDateTime assessedAt,
        UUID assessedBy
) {
}
