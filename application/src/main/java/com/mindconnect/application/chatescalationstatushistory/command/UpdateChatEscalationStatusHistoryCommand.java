package com.mindconnect.application.chatescalationstatushistory.command;

import java.time.LocalDateTime;
import java.util.UUID;

import com.mindconnect.domain.chatescalationstatushistory.model.valueobject.ChatEscalationStatusHistoryId;

public record UpdateChatEscalationStatusHistoryCommand(
        ChatEscalationStatusHistoryId id,
        UUID escalationId,
        UUID escalationStatusId,
        LocalDateTime changedAt
) {
}
