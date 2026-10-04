package com.mindconnect.application.conversationstatus.exception;

import com.mindconnect.application.common.exception.ApplicationException;
import com.mindconnect.domain.conversationstatus.model.valueobject.ConversationStatusId;

public class ConversationStatusNotFoundApplicationException extends ApplicationException {

    private static final long serialVersionUID = 1L;

    public ConversationStatusNotFoundApplicationException(ConversationStatusId id) {
        super("ConversationStatus with id " + id.value() + " was not found.");
    }
}
