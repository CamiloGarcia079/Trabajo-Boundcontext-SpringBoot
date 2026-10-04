package com.mindconnect.application.chatescalation.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import com.mindconnect.domain.chatescalation.model.aggregate.ChatEscalation;

public record ChatEscalationResponse(
        UUID id,
        UUID conversationId,
        UUID statusId,
        boolean fromAi,
        String reason,
        LocalDateTime createdAt
) {

    public static ChatEscalationResponse fromDomain(ChatEscalation aggregate) {
        return new ChatEscalationResponse(
                aggregate.id().value(),
                aggregate.conversationId(), aggregate.statusId(), aggregate.fromAi(), aggregate.reason(), aggregate.createdAt());
    }
}
