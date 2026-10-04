package com.mindconnect.application.professional.exception;

import com.mindconnect.application.common.exception.ApplicationException;
import com.mindconnect.domain.professional.model.valueobject.ProfessionalId;

public class ProfessionalNotFoundApplicationException extends ApplicationException {

    private static final long serialVersionUID = 1L;

    public ProfessionalNotFoundApplicationException(ProfessionalId id) {
        super("Professional with id " + id.value() + " was not found.");
    }
}
