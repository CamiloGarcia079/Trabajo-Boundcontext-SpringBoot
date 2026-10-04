package com.mindconnect.application.chatescalationassignment.command;

import java.time.LocalDateTime;
import java.util.UUID;

public record RegisterChatEscalationAssignmentCommand(
        UUID escalationId,
        UUID professionalId,
        LocalDateTime assignedAt
) {
}
