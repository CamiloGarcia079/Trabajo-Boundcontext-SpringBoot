package com.mindconnect.application.sendertype.command;


import com.mindconnect.domain.sendertype.model.valueobject.SenderTypeId;

public record UpdateSenderTypeCommand(
        SenderTypeId id,
        String nameType
) {
}
