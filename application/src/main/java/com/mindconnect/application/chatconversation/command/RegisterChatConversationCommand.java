package com.mindconnect.application.chatconversation.command;

import java.time.LocalDateTime;
import java.util.UUID;

public record RegisterChatConversationCommand(
        UUID conversationStatusId,
        UUID priorityId,
        LocalDateTime lastMessageAt,
        Boolean closed,
        LocalDateTime closedAt,
        UUID closedBy
) {
}
