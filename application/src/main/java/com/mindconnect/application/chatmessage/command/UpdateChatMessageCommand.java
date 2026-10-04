package com.mindconnect.application.chatmessage.command;

import java.util.UUID;

import com.mindconnect.domain.chatmessage.model.valueobject.ChatMessageId;

public record UpdateChatMessageCommand(
        ChatMessageId id,
        UUID conversationId,
        UUID messageTypeId,
        UUID participantId,
        String content,
        String metadata
) {
}
