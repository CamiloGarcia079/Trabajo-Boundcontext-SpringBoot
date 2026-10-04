package com.mindconnect.application.chatairun.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import com.mindconnect.domain.chatairun.model.aggregate.ChatAiRun;

public record ChatAiRunResponse(
        UUID id,
        UUID conversationId,
        UUID messageId,
        UUID modelId,
        UUID aiRunStatusId,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {

    public static ChatAiRunResponse fromDomain(ChatAiRun aggregate) {
        return new ChatAiRunResponse(
                aggregate.id().value(),
                aggregate.conversationId(), aggregate.messageId(), aggregate.modelId(), aggregate.aiRunStatusId(), aggregate.createdAt(), aggregate.updatedAt());
    }
}
