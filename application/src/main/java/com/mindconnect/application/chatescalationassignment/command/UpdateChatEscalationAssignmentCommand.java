package com.mindconnect.application.chatescalationassignment.command;

import java.time.LocalDateTime;
import java.util.UUID;

import com.mindconnect.domain.chatescalationassignment.model.valueobject.ChatEscalationAssignmentId;

public record UpdateChatEscalationAssignmentCommand(
        ChatEscalationAssignmentId id,
        UUID escalationId,
        UUID professionalId,
        LocalDateTime assignedAt
) {
}
