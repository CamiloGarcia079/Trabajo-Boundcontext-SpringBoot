package com.mindconnect.application.priority.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import com.mindconnect.domain.priority.model.aggregate.Priority;

public record PriorityResponse(
        UUID id,
        String namePriority,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {

    public static PriorityResponse fromDomain(Priority aggregate) {
        return new PriorityResponse(
                aggregate.id().value(),
                aggregate.namePriority(), aggregate.createdAt(), aggregate.updatedAt());
    }
}
