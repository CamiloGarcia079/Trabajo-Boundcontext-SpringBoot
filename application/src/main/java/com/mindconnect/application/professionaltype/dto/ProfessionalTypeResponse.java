package com.mindconnect.application.professionaltype.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import com.mindconnect.domain.professionaltype.model.aggregate.ProfessionalType;

public record ProfessionalTypeResponse(
        UUID id,
        String name,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {

    public static ProfessionalTypeResponse fromDomain(ProfessionalType aggregate) {
        return new ProfessionalTypeResponse(
                aggregate.id().value(),
                aggregate.name(), aggregate.createdAt(), aggregate.updatedAt());
    }
}
