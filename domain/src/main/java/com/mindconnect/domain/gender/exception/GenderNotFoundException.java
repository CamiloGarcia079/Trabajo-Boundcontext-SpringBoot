package com.mindconnect.domain.gender.exception;

import com.mindconnect.domain.common.exception.DomainException;
import com.mindconnect.domain.gender.model.valueobject.GenderId;

public class GenderNotFoundException extends DomainException {

    private static final long serialVersionUID = 1L;

    public GenderNotFoundException(GenderId id) {
        super("Gender with id " + id.value() + " was not found.");
    }
}
