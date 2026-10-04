package com.mindconnect.application.conversationstatus.command;


import com.mindconnect.domain.conversationstatus.model.valueobject.ConversationStatusId;

public record UpdateConversationStatusCommand(
        ConversationStatusId id,
        String nameStatus
) {
}
