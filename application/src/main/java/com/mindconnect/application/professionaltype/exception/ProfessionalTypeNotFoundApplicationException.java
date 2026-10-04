package com.mindconnect.application.professionaltype.exception;

import com.mindconnect.application.common.exception.ApplicationException;
import com.mindconnect.domain.professionaltype.model.valueobject.ProfessionalTypeId;

public class ProfessionalTypeNotFoundApplicationException extends ApplicationException {

    private static final long serialVersionUID = 1L;

    public ProfessionalTypeNotFoundApplicationException(ProfessionalTypeId id) {
        super("ProfessionalType with id " + id.value() + " was not found.");
    }
}
