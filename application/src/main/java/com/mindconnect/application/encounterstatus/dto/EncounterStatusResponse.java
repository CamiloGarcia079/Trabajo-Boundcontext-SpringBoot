package com.mindconnect.application.encounterstatus.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import com.mindconnect.domain.encounterstatus.model.aggregate.EncounterStatus;

public record EncounterStatusResponse(
        UUID id,
        String code,
        String name,
        boolean active,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {

    public static EncounterStatusResponse fromDomain(EncounterStatus aggregate) {
        return new EncounterStatusResponse(
                aggregate.id().value(),
                aggregate.code(), aggregate.name(), aggregate.active(), aggregate.createdAt(), aggregate.updatedAt());
    }
}
