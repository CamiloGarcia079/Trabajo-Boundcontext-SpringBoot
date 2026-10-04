package com.mindconnect.domain.conversationstatus.exception;

import com.mindconnect.domain.common.exception.DomainException;
import com.mindconnect.domain.conversationstatus.model.valueobject.ConversationStatusId;

public class ConversationStatusNotFoundException extends DomainException {

    private static final long serialVersionUID = 1L;

    public ConversationStatusNotFoundException(ConversationStatusId id) {
        super("ConversationStatus with id " + id.value() + " was not found.");
    }
}
