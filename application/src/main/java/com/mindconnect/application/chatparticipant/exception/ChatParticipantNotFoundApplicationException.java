package com.mindconnect.application.chatparticipant.exception;

import com.mindconnect.application.common.exception.ApplicationException;
import com.mindconnect.domain.chatparticipant.model.valueobject.ChatParticipantId;

public class ChatParticipantNotFoundApplicationException extends ApplicationException {

    private static final long serialVersionUID = 1L;

    public ChatParticipantNotFoundApplicationException(ChatParticipantId id) {
        super("ChatParticipant with id " + id.value() + " was not found.");
    }
}
