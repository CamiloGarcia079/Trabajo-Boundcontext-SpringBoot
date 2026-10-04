package com.mindconnect.application.contact.exception;

import com.mindconnect.application.common.exception.ApplicationException;
import com.mindconnect.domain.contact.model.valueobject.ContactId;

public class ContactNotFoundApplicationException extends ApplicationException {

    private static final long serialVersionUID = 1L;

    public ContactNotFoundApplicationException(ContactId id) {
        super("Contact with id " + id.value() + " was not found.");
    }
}
