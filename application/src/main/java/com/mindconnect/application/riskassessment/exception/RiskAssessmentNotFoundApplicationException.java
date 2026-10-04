package com.mindconnect.application.riskassessment.exception;

import com.mindconnect.application.common.exception.ApplicationException;
import com.mindconnect.domain.riskassessment.model.valueobject.RiskAssessmentId;

public class RiskAssessmentNotFoundApplicationException extends ApplicationException {

    private static final long serialVersionUID = 1L;

    public RiskAssessmentNotFoundApplicationException(RiskAssessmentId id) {
        super("RiskAssessment with id " + id.value() + " was not found.");
    }
}
