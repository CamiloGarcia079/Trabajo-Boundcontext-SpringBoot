package com.mindconnect.application.messagetype.exception;

import com.mindconnect.application.common.exception.ApplicationException;
import com.mindconnect.domain.messagetype.model.valueobject.MessageTypeId;

public class MessageTypeNotFoundApplicationException extends ApplicationException {

    private static final long serialVersionUID = 1L;

    public MessageTypeNotFoundApplicationException(MessageTypeId id) {
        super("MessageType with id " + id.value() + " was not found.");
    }
}
