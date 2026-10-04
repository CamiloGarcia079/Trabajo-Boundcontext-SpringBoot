package com.mindconnect.application.chatescalationstatushistory.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import com.mindconnect.domain.chatescalationstatushistory.model.aggregate.ChatEscalationStatusHistory;

public record ChatEscalationStatusHistoryResponse(
        UUID id,
        UUID escalationId,
        UUID escalationStatusId,
        LocalDateTime createdAt,
        LocalDateTime changedAt
) {

    public static ChatEscalationStatusHistoryResponse fromDomain(ChatEscalationStatusHistory aggregate) {
        return new ChatEscalationStatusHistoryResponse(
                aggregate.id().value(),
                aggregate.escalationId(), aggregate.escalationStatusId(), aggregate.createdAt(), aggregate.changedAt());
    }
}
