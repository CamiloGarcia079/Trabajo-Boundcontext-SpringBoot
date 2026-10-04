package com.mindconnect.application.professionalstudy.command;

import java.util.UUID;

import com.mindconnect.domain.professionalstudy.model.valueobject.ProfessionalStudyId;

public record UpdateProfessionalStudyCommand(
        ProfessionalStudyId id,
        UUID studyId,
        UUID professionalId,
        String title,
        String university,
        boolean isValid,
        String resolutionNumber,
        UUID countryId
) {
}
