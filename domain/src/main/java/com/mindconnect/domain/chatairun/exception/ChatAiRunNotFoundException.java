package com.mindconnect.domain.chatairun.exception;

import com.mindconnect.domain.common.exception.DomainException;
import com.mindconnect.domain.chatairun.model.valueobject.ChatAiRunId;

public class ChatAiRunNotFoundException extends DomainException {

    private static final long serialVersionUID = 1L;

    public ChatAiRunNotFoundException(ChatAiRunId id) {
        super("ChatAiRun with id " + id.value() + " was not found.");
    }
}
