package com.mindconnect.application.chatescalationassignment.exception;

import com.mindconnect.application.common.exception.ApplicationException;
import com.mindconnect.domain.chatescalationassignment.model.valueobject.ChatEscalationAssignmentId;

public class ChatEscalationAssignmentNotFoundApplicationException extends ApplicationException {

    private static final long serialVersionUID = 1L;

    public ChatEscalationAssignmentNotFoundApplicationException(ChatEscalationAssignmentId id) {
        super("ChatEscalationAssignment with id " + id.value() + " was not found.");
    }
}
