package com.mindconnect.domain.messagetype.exception;

import com.mindconnect.domain.common.exception.DomainException;
import com.mindconnect.domain.messagetype.model.valueobject.MessageTypeId;

public class MessageTypeNotFoundException extends DomainException {

    private static final long serialVersionUID = 1L;

    public MessageTypeNotFoundException(MessageTypeId id) {
        super("MessageType with id " + id.value() + " was not found.");
    }
}
