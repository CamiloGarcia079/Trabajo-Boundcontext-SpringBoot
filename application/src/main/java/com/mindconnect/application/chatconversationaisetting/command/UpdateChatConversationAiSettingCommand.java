package com.mindconnect.application.chatconversationaisetting.command;

import java.util.UUID;

import com.mindconnect.domain.chatconversationaisetting.model.valueobject.ChatConversationAiSettingId;

public record UpdateChatConversationAiSettingCommand(
        ChatConversationAiSettingId id,
        UUID conversationId,
        boolean aiEnabled,
        UUID defaultModelId
) {
}
