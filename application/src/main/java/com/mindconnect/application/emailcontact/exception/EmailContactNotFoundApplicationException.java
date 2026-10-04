package com.mindconnect.application.emailcontact.exception;

import com.mindconnect.application.common.exception.ApplicationException;
import com.mindconnect.domain.emailcontact.model.valueobject.EmailContactId;

public class EmailContactNotFoundApplicationException extends ApplicationException {

    private static final long serialVersionUID = 1L;

    public EmailContactNotFoundApplicationException(EmailContactId id) {
        super("EmailContact with id " + id.value() + " was not found.");
    }
}
