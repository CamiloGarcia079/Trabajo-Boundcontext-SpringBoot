package com.mindconnect.application.chatescalation.command;

import java.util.UUID;

import com.mindconnect.domain.chatescalation.model.valueobject.ChatEscalationId;

public record UpdateChatEscalationCommand(
        ChatEscalationId id,
        UUID conversationId,
        UUID statusId,
        boolean fromAi,
        String reason
) {
}
