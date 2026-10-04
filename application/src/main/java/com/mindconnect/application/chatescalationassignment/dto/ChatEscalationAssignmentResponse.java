package com.mindconnect.application.chatescalationassignment.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import com.mindconnect.domain.chatescalationassignment.model.aggregate.ChatEscalationAssignment;

public record ChatEscalationAssignmentResponse(
        UUID id,
        UUID escalationId,
        UUID professionalId,
        LocalDateTime assignedAt
) {

    public static ChatEscalationAssignmentResponse fromDomain(ChatEscalationAssignment aggregate) {
        return new ChatEscalationAssignmentResponse(
                aggregate.id().value(),
                aggregate.escalationId(), aggregate.professionalId(), aggregate.assignedAt());
    }
}
