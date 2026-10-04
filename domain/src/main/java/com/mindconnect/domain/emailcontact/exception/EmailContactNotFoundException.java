package com.mindconnect.domain.emailcontact.exception;

import com.mindconnect.domain.common.exception.DomainException;
import com.mindconnect.domain.emailcontact.model.valueobject.EmailContactId;

public class EmailContactNotFoundException extends DomainException {

    private static final long serialVersionUID = 1L;

    public EmailContactNotFoundException(EmailContactId id) {
        super("EmailContact with id " + id.value() + " was not found.");
    }
}
