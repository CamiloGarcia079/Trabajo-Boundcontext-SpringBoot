package com.mindconnect.application.chatconversationaisetting.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import com.mindconnect.domain.chatconversationaisetting.model.aggregate.ChatConversationAiSetting;

public record ChatConversationAiSettingResponse(
        UUID id,
        UUID conversationId,
        boolean aiEnabled,
        UUID defaultModelId,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {

    public static ChatConversationAiSettingResponse fromDomain(ChatConversationAiSetting aggregate) {
        return new ChatConversationAiSettingResponse(
                aggregate.id().value(),
                aggregate.conversationId(), aggregate.aiEnabled(), aggregate.defaultModelId(), aggregate.createdAt(), aggregate.updatedAt());
    }
}
