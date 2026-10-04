package com.mindconnect.domain.chatairunerror.exception;

import com.mindconnect.domain.common.exception.DomainException;
import com.mindconnect.domain.chatairunerror.model.valueobject.ChatAiRunErrorId;

public class ChatAiRunErrorNotFoundException extends DomainException {

    private static final long serialVersionUID = 1L;

    public ChatAiRunErrorNotFoundException(ChatAiRunErrorId id) {
        super("ChatAiRunError with id " + id.value() + " was not found.");
    }
}
