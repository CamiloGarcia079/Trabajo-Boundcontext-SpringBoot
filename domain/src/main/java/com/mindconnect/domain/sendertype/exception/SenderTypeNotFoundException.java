package com.mindconnect.domain.sendertype.exception;

import com.mindconnect.domain.common.exception.DomainException;
import com.mindconnect.domain.sendertype.model.valueobject.SenderTypeId;

public class SenderTypeNotFoundException extends DomainException {

    private static final long serialVersionUID = 1L;

    public SenderTypeNotFoundException(SenderTypeId id) {
        super("SenderType with id " + id.value() + " was not found.");
    }
}
