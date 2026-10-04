package com.mindconnect.application.conversationstatus.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import com.mindconnect.domain.conversationstatus.model.aggregate.ConversationStatus;

public record ConversationStatusResponse(
        UUID id,
        String nameStatus,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {

    public static ConversationStatusResponse fromDomain(ConversationStatus aggregate) {
        return new ConversationStatusResponse(
                aggregate.id().value(),
                aggregate.nameStatus(), aggregate.createdAt(), aggregate.updatedAt());
    }
}
