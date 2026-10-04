package com.mindconnect.application.chatairun.exception;

import com.mindconnect.application.common.exception.ApplicationException;
import com.mindconnect.domain.chatairun.model.valueobject.ChatAiRunId;

public class ChatAiRunNotFoundApplicationException extends ApplicationException {

    private static final long serialVersionUID = 1L;

    public ChatAiRunNotFoundApplicationException(ChatAiRunId id) {
        super("ChatAiRun with id " + id.value() + " was not found.");
    }
}
