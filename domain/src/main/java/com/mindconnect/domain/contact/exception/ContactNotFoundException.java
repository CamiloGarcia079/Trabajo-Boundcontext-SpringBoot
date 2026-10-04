package com.mindconnect.domain.contact.exception;

import com.mindconnect.domain.common.exception.DomainException;
import com.mindconnect.domain.contact.model.valueobject.ContactId;

public class ContactNotFoundException extends DomainException {

    private static final long serialVersionUID = 1L;

    public ContactNotFoundException(ContactId id) {
        super("Contact with id " + id.value() + " was not found.");
    }
}
