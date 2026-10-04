package com.mindconnect.domain.chatescalationstatushistory.exception;

import com.mindconnect.domain.common.exception.DomainException;
import com.mindconnect.domain.chatescalationstatushistory.model.valueobject.ChatEscalationStatusHistoryId;

public class ChatEscalationStatusHistoryNotFoundException extends DomainException {

    private static final long serialVersionUID = 1L;

    public ChatEscalationStatusHistoryNotFoundException(ChatEscalationStatusHistoryId id) {
        super("ChatEscalationStatusHistory with id " + id.value() + " was not found.");
    }
}
