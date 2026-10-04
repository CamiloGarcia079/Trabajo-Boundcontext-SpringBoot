package com.mindconnect.application.assessmenttype.exception;

import com.mindconnect.application.common.exception.ApplicationException;
import com.mindconnect.domain.assessmenttype.model.valueobject.AssessmentTypeId;

public class AssessmentTypeNotFoundApplicationException extends ApplicationException {

    private static final long serialVersionUID = 1L;

    public AssessmentTypeNotFoundApplicationException(AssessmentTypeId id) {
        super("AssessmentType with id " + id.value() + " was not found.");
    }
}
