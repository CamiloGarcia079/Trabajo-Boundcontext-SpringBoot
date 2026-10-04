package com.mindconnect.application.riskassessment.usecase;

import com.mindconnect.application.riskassessment.command.RegisterRiskAssessmentCommand;
import com.mindconnect.application.riskassessment.dto.RiskAssessmentResponse;
import com.mindconnect.domain.riskassessment.model.aggregate.RiskAssessment;
import com.mindconnect.domain.riskassessment.port.repository.RiskAssessmentRepository;

public class RegisterRiskAssessmentUseCase {

    private final RiskAssessmentRepository repository;

    public RegisterRiskAssessmentUseCase(RiskAssessmentRepository repository) {
        this.repository = repository;
    }

    public RiskAssessmentResponse execute(RegisterRiskAssessmentCommand command) {
        RiskAssessment aggregate = RiskAssessment.register(
                command.encounterId(), command.riskLevelId(), command.suicidalIdeation(), command.suicidePlan(), command.suicideIntent(), command.selfHarm(), command.harmToOthers(), command.riskFactors(), command.protectiveFactors(), command.clinicalActions(), command.observations(), command.assessedAt(), command.assessedBy());
        RiskAssessment saved = repository.save(aggregate);
        return RiskAssessmentResponse.fromDomain(saved);
    }
}
