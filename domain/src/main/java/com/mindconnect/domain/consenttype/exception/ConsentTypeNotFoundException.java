package com.mindconnect.domain.consenttype.exception;

import com.mindconnect.domain.common.exception.DomainException;
import com.mindconnect.domain.consenttype.model.valueobject.ConsentTypeId;

public class ConsentTypeNotFoundException extends DomainException {

    private static final long serialVersionUID = 1L;

    public ConsentTypeNotFoundException(ConsentTypeId id) {
        super("ConsentType with id " + id.value() + " was not found.");
    }
}
