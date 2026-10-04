package com.mindconnect.application.riskassessment.usecase;

import com.mindconnect.application.riskassessment.command.UpdateRiskAssessmentCommand;
import com.mindconnect.application.riskassessment.dto.RiskAssessmentResponse;
import com.mindconnect.application.riskassessment.exception.RiskAssessmentNotFoundApplicationException;
import com.mindconnect.domain.riskassessment.model.aggregate.RiskAssessment;
import com.mindconnect.domain.riskassessment.port.repository.RiskAssessmentRepository;

public class UpdateRiskAssessmentUseCase {

    private final RiskAssessmentRepository repository;

    public UpdateRiskAssessmentUseCase(RiskAssessmentRepository repository) {
        this.repository = repository;
    }

    public RiskAssessmentResponse execute(UpdateRiskAssessmentCommand command) {
        RiskAssessment aggregate = repository.findById(command.id())
                .orElseThrow(() -> new RiskAssessmentNotFoundApplicationException(command.id()));
        aggregate.update(
                command.encounterId(), command.riskLevelId(), command.suicidalIdeation(), command.suicidePlan(), command.suicideIntent(), command.selfHarm(), command.harmToOthers(), command.riskFactors(), command.protectiveFactors(), command.clinicalActions(), command.observations(), command.assessedAt(), command.assessedBy());
        RiskAssessment saved = repository.save(aggregate);
        return RiskAssessmentResponse.fromDomain(saved);
    }
}
