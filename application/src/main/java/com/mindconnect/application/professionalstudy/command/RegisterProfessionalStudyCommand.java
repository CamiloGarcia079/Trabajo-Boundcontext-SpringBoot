package com.mindconnect.application.professionalstudy.command;

import java.util.UUID;

public record RegisterProfessionalStudyCommand(
        UUID studyId,
        UUID professionalId,
        String title,
        String university,
        boolean isValid,
        String resolutionNumber,
        UUID countryId
) {
}
