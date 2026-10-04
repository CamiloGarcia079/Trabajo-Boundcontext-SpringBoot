package com.mindconnect.domain.assessmenttype.exception;

import com.mindconnect.domain.common.exception.DomainException;
import com.mindconnect.domain.assessmenttype.model.valueobject.AssessmentTypeId;

public class AssessmentTypeNotFoundException extends DomainException {

    private static final long serialVersionUID = 1L;

    public AssessmentTypeNotFoundException(AssessmentTypeId id) {
        super("AssessmentType with id " + id.value() + " was not found.");
    }
}
