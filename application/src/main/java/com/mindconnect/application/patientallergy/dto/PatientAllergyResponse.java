package com.mindconnect.application.patientallergy.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import com.mindconnect.domain.patientallergy.model.aggregate.PatientAllergy;

public record PatientAllergyResponse(
        UUID id,
        UUID patientId,
        String substance,
        String reaction,
        String severity,
        boolean active,
        LocalDateTime recordedAt,
        UUID recordedBy,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {

    public static PatientAllergyResponse fromDomain(PatientAllergy aggregate) {
        return new PatientAllergyResponse(
                aggregate.id().value(),
                aggregate.patientId(), aggregate.substance(), aggregate.reaction(), aggregate.severity(), aggregate.active(), aggregate.recordedAt(), aggregate.recordedBy(), aggregate.createdAt(), aggregate.updatedAt());
    }
}
