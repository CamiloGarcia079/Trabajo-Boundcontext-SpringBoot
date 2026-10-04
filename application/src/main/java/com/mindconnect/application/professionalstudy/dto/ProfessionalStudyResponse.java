package com.mindconnect.application.professionalstudy.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import com.mindconnect.domain.professionalstudy.model.aggregate.ProfessionalStudy;

public record ProfessionalStudyResponse(
        UUID id,
        UUID studyId,
        UUID professionalId,
        String title,
        String university,
        boolean isValid,
        String resolutionNumber,
        UUID countryId,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {

    public static ProfessionalStudyResponse fromDomain(ProfessionalStudy aggregate) {
        return new ProfessionalStudyResponse(
                aggregate.id().value(),
                aggregate.studyId(), aggregate.professionalId(), aggregate.title(), aggregate.university(), aggregate.isValid(), aggregate.resolutionNumber(), aggregate.countryId(), aggregate.createdAt(), aggregate.updatedAt());
    }
}
