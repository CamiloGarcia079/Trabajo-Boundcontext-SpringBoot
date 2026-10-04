package com.mindconnect.domain.riskassessment.exception;

import com.mindconnect.domain.common.exception.DomainException;
import com.mindconnect.domain.riskassessment.model.valueobject.RiskAssessmentId;

public class RiskAssessmentNotFoundException extends DomainException {

    private static final long serialVersionUID = 1L;

    public RiskAssessmentNotFoundException(RiskAssessmentId id) {
        super("RiskAssessment with id " + id.value() + " was not found.");
    }
}
