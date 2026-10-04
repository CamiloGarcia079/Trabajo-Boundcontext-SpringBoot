package com.mindconnect.domain.chatmessage.exception;

import com.mindconnect.domain.common.exception.DomainException;
import com.mindconnect.domain.chatmessage.model.valueobject.ChatMessageId;

public class ChatMessageNotFoundException extends DomainException {

    private static final long serialVersionUID = 1L;

    public ChatMessageNotFoundException(ChatMessageId id) {
        super("ChatMessage with id " + id.value() + " was not found.");
    }
}
