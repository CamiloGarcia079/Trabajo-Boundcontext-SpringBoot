package com.mindconnect.application.sendertype.exception;

import com.mindconnect.application.common.exception.ApplicationException;
import com.mindconnect.domain.sendertype.model.valueobject.SenderTypeId;

public class SenderTypeNotFoundApplicationException extends ApplicationException {

    private static final long serialVersionUID = 1L;

    public SenderTypeNotFoundApplicationException(SenderTypeId id) {
        super("SenderType with id " + id.value() + " was not found.");
    }
}
