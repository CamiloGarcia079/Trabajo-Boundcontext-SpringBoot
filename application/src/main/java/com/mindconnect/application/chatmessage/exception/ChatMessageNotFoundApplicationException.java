package com.mindconnect.application.chatmessage.exception;

import com.mindconnect.application.common.exception.ApplicationException;
import com.mindconnect.domain.chatmessage.model.valueobject.ChatMessageId;

public class ChatMessageNotFoundApplicationException extends ApplicationException {

    private static final long serialVersionUID = 1L;

    public ChatMessageNotFoundApplicationException(ChatMessageId id) {
        super("ChatMessage with id " + id.value() + " was not found.");
    }
}
