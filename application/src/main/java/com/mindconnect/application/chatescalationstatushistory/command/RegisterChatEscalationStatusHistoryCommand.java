package com.mindconnect.application.chatescalationstatushistory.command;

import java.time.LocalDateTime;
import java.util.UUID;

public record RegisterChatEscalationStatusHistoryCommand(
        UUID escalationId,
        UUID escalationStatusId,
        LocalDateTime changedAt
) {
}
