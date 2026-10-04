package com.mindconnect.application.professional.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import com.mindconnect.domain.professional.model.aggregate.Professional;

public record ProfessionalResponse(
        UUID id,
        UUID documentTypeId,
        String documentNumber,
        String firstName,
        String lastName,
        UUID professionalType,
        String licenseNumber,
        boolean active,
        UUID cityId,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {

    public static ProfessionalResponse fromDomain(Professional aggregate) {
        return new ProfessionalResponse(
                aggregate.id().value(),
                aggregate.documentTypeId(), aggregate.documentNumber(), aggregate.firstName(), aggregate.lastName(), aggregate.professionalType(), aggregate.licenseNumber(), aggregate.active(), aggregate.cityId(), aggregate.createdAt(), aggregate.updatedAt());
    }
}
