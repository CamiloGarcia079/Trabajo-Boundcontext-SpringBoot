package com.mindconnect.application.professional.command;

import java.util.UUID;

import com.mindconnect.domain.professional.model.valueobject.ProfessionalId;

public record UpdateProfessionalCommand(
        ProfessionalId id,
        UUID documentTypeId,
        String documentNumber,
        String firstName,
        String lastName,
        UUID professionalType,
        String licenseNumber,
        UUID cityId
) {
}
