package com.mindconnect.application.riskassessment.usecase;

import java.time.LocalDateTime;

import com.mindconnect.application.riskassessment.exception.RiskAssessmentNotFoundApplicationException;
import com.mindconnect.domain.riskassessment.event.RiskAssessmentDeletedEvent;
import com.mindconnect.domain.riskassessment.model.valueobject.RiskAssessmentId;
import com.mindconnect.domain.riskassessment.port.repository.RiskAssessmentRepository;

public class DeleteRiskAssessmentUseCase {

    private final RiskAssessmentRepository repository;

    public DeleteRiskAssessmentUseCase(RiskAssessmentRepository repository) {
        this.repository = repository;
    }

    public RiskAssessmentDeletedEvent execute(RiskAssessmentId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new RiskAssessmentNotFoundApplicationException(id));

        repository.delete(aggregate);

        return new RiskAssessmentDeletedEvent(id, LocalDateTime.now());
    }
}
