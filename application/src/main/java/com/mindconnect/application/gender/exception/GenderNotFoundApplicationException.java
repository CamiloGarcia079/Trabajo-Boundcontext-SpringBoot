package com.mindconnect.application.gender.exception;

import com.mindconnect.application.common.exception.ApplicationException;
import com.mindconnect.domain.gender.model.valueobject.GenderId;

public class GenderNotFoundApplicationException extends ApplicationException {

    private static final long serialVersionUID = 1L;

    public GenderNotFoundApplicationException(GenderId id) {
        super("Gender with id " + id.value() + " was not found.");
    }
}
