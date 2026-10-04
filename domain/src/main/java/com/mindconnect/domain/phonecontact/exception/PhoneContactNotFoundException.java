package com.mindconnect.domain.phonecontact.exception;

import com.mindconnect.domain.common.exception.DomainException;
import com.mindconnect.domain.phonecontact.model.valueobject.PhoneContactId;

public class PhoneContactNotFoundException extends DomainException {

    private static final long serialVersionUID = 1L;

    public PhoneContactNotFoundException(PhoneContactId id) {
        super("PhoneContact with id " + id.value() + " was not found.");
    }
}
