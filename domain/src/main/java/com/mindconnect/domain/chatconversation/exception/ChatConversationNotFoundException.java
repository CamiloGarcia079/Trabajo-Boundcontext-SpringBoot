package com.mindconnect.domain.chatconversation.exception;

import com.mindconnect.domain.common.exception.DomainException;
import com.mindconnect.domain.chatconversation.model.valueobject.ChatConversationId;

public class ChatConversationNotFoundException extends DomainException {

    private static final long serialVersionUID = 1L;

    public ChatConversationNotFoundException(ChatConversationId id) {
        super("ChatConversation with id " + id.value() + " was not found.");
    }
}
