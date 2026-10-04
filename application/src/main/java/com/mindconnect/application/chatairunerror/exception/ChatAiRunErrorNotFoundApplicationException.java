package com.mindconnect.application.chatairunerror.exception;

import com.mindconnect.application.common.exception.ApplicationException;
import com.mindconnect.domain.chatairunerror.model.valueobject.ChatAiRunErrorId;

public class ChatAiRunErrorNotFoundApplicationException extends ApplicationException {

    private static final long serialVersionUID = 1L;

    public ChatAiRunErrorNotFoundApplicationException(ChatAiRunErrorId id) {
        super("ChatAiRunError with id " + id.value() + " was not found.");
    }
}
