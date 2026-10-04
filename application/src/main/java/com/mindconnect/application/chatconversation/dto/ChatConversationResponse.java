package com.mindconnect.application.chatconversation.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import com.mindconnect.domain.chatconversation.model.aggregate.ChatConversation;

public record ChatConversationResponse(
        UUID id,
        UUID conversationStatusId,
        UUID priorityId,
        LocalDateTime lastMessageAt,
        Boolean closed,
        LocalDateTime closedAt,
        UUID closedBy,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {

    public static ChatConversationResponse fromDomain(ChatConversation aggregate) {
        return new ChatConversationResponse(
                aggregate.id().value(),
                aggregate.conversationStatusId(), aggregate.priorityId(), aggregate.lastMessageAt(), aggregate.closed(), aggregate.closedAt(), aggregate.closedBy(), aggregate.createdAt(), aggregate.updatedAt());
    }
}
