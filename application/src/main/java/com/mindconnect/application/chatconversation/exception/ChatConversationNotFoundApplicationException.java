package com.mindconnect.application.chatconversation.exception;

import com.mindconnect.application.common.exception.ApplicationException;
import com.mindconnect.domain.chatconversation.model.valueobject.ChatConversationId;

public class ChatConversationNotFoundApplicationException extends ApplicationException {

    private static final long serialVersionUID = 1L;

    public ChatConversationNotFoundApplicationException(ChatConversationId id) {
        super("ChatConversation with id " + id.value() + " was not found.");
    }
}
