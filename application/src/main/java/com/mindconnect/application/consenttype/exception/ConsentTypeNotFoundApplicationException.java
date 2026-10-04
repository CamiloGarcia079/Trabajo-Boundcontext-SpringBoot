package com.mindconnect.application.consenttype.exception;

import com.mindconnect.application.common.exception.ApplicationException;
import com.mindconnect.domain.consenttype.model.valueobject.ConsentTypeId;

public class ConsentTypeNotFoundApplicationException extends ApplicationException {

    private static final long serialVersionUID = 1L;

    public ConsentTypeNotFoundApplicationException(ConsentTypeId id) {
        super("ConsentType with id " + id.value() + " was not found.");
    }
}
