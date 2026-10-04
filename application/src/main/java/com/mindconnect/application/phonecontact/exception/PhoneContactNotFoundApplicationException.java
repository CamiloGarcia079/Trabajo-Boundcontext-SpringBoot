package com.mindconnect.application.phonecontact.exception;

import com.mindconnect.application.common.exception.ApplicationException;
import com.mindconnect.domain.phonecontact.model.valueobject.PhoneContactId;

public class PhoneContactNotFoundApplicationException extends ApplicationException {

    private static final long serialVersionUID = 1L;

    public PhoneContactNotFoundApplicationException(PhoneContactId id) {
        super("PhoneContact with id " + id.value() + " was not found.");
    }
}
