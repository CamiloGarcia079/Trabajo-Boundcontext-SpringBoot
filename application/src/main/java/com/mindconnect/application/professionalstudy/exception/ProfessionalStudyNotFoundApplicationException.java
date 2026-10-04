package com.mindconnect.application.professionalstudy.exception;

import com.mindconnect.application.common.exception.ApplicationException;
import com.mindconnect.domain.professionalstudy.model.valueobject.ProfessionalStudyId;

public class ProfessionalStudyNotFoundApplicationException extends ApplicationException {

    private static final long serialVersionUID = 1L;

    public ProfessionalStudyNotFoundApplicationException(ProfessionalStudyId id) {
        super("ProfessionalStudy with id " + id.value() + " was not found.");
    }
}
