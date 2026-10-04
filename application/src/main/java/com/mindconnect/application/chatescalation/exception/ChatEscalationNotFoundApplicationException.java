package com.mindconnect.application.chatescalation.exception;

import com.mindconnect.application.common.exception.ApplicationException;
import com.mindconnect.domain.chatescalation.model.valueobject.ChatEscalationId;

public class ChatEscalationNotFoundApplicationException extends ApplicationException {

    private static final long serialVersionUID = 1L;

    public ChatEscalationNotFoundApplicationException(ChatEscalationId id) {
        super("ChatEscalation with id " + id.value() + " was not found.");
    }
}
