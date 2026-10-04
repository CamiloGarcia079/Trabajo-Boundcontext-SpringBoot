package com.mindconnect.application.chatconversationaisetting.command;

import java.util.UUID;

public record RegisterChatConversationAiSettingCommand(
        UUID conversationId,
        boolean aiEnabled,
        UUID defaultModelId
) {
}
