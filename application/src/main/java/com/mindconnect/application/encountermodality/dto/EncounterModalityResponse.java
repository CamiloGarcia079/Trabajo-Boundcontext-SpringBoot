package com.mindconnect.application.encountermodality.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import com.mindconnect.domain.encountermodality.model.aggregate.EncounterModality;

public record EncounterModalityResponse(
        UUID id,
        String code,
        String name,
        boolean active,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {

    public static EncounterModalityResponse fromDomain(EncounterModality aggregate) {
        return new EncounterModalityResponse(
                aggregate.id().value(),
                aggregate.code(), aggregate.name(), aggregate.active(), aggregate.createdAt(), aggregate.updatedAt());
    }
}
