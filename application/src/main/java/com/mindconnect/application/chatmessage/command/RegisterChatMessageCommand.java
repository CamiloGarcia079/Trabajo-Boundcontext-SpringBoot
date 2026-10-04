package com.mindconnect.application.chatmessage.command;

import java.util.UUID;

public record RegisterChatMessageCommand(
        UUID conversationId,
        UUID messageTypeId,
        UUID participantId,
        String content,
        String metadata
) {
}
