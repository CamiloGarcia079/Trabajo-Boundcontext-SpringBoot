package com.mindconnect.application.messagetype.command;


import com.mindconnect.domain.messagetype.model.valueobject.MessageTypeId;

public record UpdateMessageTypeCommand(
        MessageTypeId id,
        String nameType
) {
}
