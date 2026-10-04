package com.mindconnect.application.chatescalationstatushistory.exception;

import com.mindconnect.application.common.exception.ApplicationException;
import com.mindconnect.domain.chatescalationstatushistory.model.valueobject.ChatEscalationStatusHistoryId;

public class ChatEscalationStatusHistoryNotFoundApplicationException extends ApplicationException {

    private static final long serialVersionUID = 1L;

    public ChatEscalationStatusHistoryNotFoundApplicationException(ChatEscalationStatusHistoryId id) {
        super("ChatEscalationStatusHistory with id " + id.value() + " was not found.");
    }
}
