package com.mindconnect.application.chatairunerror.command;

import java.util.UUID;

import com.mindconnect.domain.chatairunerror.model.valueobject.ChatAiRunErrorId;

public record UpdateChatAiRunErrorCommand(
        ChatAiRunErrorId id,
        UUID aiRunId,
        String errorMessage,
        String errorCode,
        String providerErrorId
) {
}
