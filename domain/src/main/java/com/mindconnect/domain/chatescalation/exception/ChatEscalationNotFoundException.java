package com.mindconnect.domain.chatescalation.exception;

import com.mindconnect.domain.common.exception.DomainException;
import com.mindconnect.domain.chatescalation.model.valueobject.ChatEscalationId;

public class ChatEscalationNotFoundException extends DomainException {

    private static final long serialVersionUID = 1L;

    public ChatEscalationNotFoundException(ChatEscalationId id) {
        super("ChatEscalation with id " + id.value() + " was not found.");
    }
}
