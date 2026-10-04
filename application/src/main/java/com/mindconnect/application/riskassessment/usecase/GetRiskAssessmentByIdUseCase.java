package com.mindconnect.application.riskassessment.usecase;

import com.mindconnect.application.riskassessment.dto.RiskAssessmentResponse;
import com.mindconnect.application.riskassessment.exception.RiskAssessmentNotFoundApplicationException;
import com.mindconnect.domain.riskassessment.model.valueobject.RiskAssessmentId;
import com.mindconnect.domain.riskassessment.port.repository.RiskAssessmentRepository;

public class GetRiskAssessmentByIdUseCase {

    private final RiskAssessmentRepository repository;

    public GetRiskAssessmentByIdUseCase(RiskAssessmentRepository repository) {
        this.repository = repository;
    }

    public RiskAssessmentResponse execute(RiskAssessmentId id) {
        return repository.findById(id)
                .map(RiskAssessmentResponse::fromDomain)
                .orElseThrow(() -> new RiskAssessmentNotFoundApplicationException(id));
    }
}
