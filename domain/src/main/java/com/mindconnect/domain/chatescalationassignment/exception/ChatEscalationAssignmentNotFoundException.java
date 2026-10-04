package com.mindconnect.domain.chatescalationassignment.exception;

import com.mindconnect.domain.common.exception.DomainException;
import com.mindconnect.domain.chatescalationassignment.model.valueobject.ChatEscalationAssignmentId;

public class ChatEscalationAssignmentNotFoundException extends DomainException {

    private static final long serialVersionUID = 1L;

    public ChatEscalationAssignmentNotFoundException(ChatEscalationAssignmentId id) {
        super("ChatEscalationAssignment with id " + id.value() + " was not found.");
    }
}
