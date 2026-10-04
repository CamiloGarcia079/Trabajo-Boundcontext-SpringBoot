package com.mindconnect.domain.chatparticipant.exception;

import com.mindconnect.domain.common.exception.DomainException;
import com.mindconnect.domain.chatparticipant.model.valueobject.ChatParticipantId;

public class ChatParticipantNotFoundException extends DomainException {

    private static final long serialVersionUID = 1L;

    public ChatParticipantNotFoundException(ChatParticipantId id) {
        super("ChatParticipant with id " + id.value() + " was not found.");
    }
}
