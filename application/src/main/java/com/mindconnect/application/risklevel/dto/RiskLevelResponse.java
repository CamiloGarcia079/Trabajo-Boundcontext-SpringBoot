package com.mindconnect.application.risklevel.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import com.mindconnect.domain.risklevel.model.aggregate.RiskLevel;

public record RiskLevelResponse(
        UUID id,
        String code,
        String name,
        boolean active,
        Integer severity,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {

    public static RiskLevelResponse fromDomain(RiskLevel aggregate) {
        return new RiskLevelResponse(
                aggregate.id().value(),
                aggregate.code(), aggregate.name(), aggregate.active(), aggregate.severity(), aggregate.createdAt(), aggregate.updatedAt());
    }
}
