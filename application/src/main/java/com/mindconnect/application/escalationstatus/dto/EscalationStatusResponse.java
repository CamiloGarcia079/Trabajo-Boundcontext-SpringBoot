package com.mindconnect.application.escalationstatus.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import com.mindconnect.domain.escalationstatus.model.aggregate.EscalationStatus;

public record EscalationStatusResponse(
        UUID id,
        String nameStatus,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {

    public static EscalationStatusResponse fromDomain(EscalationStatus aggregate) {
        return new EscalationStatusResponse(
                aggregate.id().value(),
                aggregate.nameStatus(), aggregate.createdAt(), aggregate.updatedAt());
    }
}
