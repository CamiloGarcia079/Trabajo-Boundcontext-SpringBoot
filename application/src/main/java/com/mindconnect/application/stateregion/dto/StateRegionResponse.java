package com.mindconnect.application.stateregion.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import com.mindconnect.domain.stateregion.model.aggregate.StateRegion;

public record StateRegionResponse(
        UUID id,
        String nameRegion,
        String codeRegion,
        String description,
        boolean isActive,
        UUID countryId,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {

    public static StateRegionResponse fromDomain(StateRegion aggregate) {
        return new StateRegionResponse(
                aggregate.id().value(),
                aggregate.nameRegion(), aggregate.codeRegion(), aggregate.description(), aggregate.isActive(), aggregate.countryId(), aggregate.createdAt(), aggregate.updatedAt());
    }
}
