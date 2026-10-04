package com.mindconnect.application.professional.command;

import java.util.UUID;

public record RegisterProfessionalCommand(
        UUID documentTypeId,
        String documentNumber,
        String firstName,
        String lastName,
        UUID professionalType,
        String licenseNumber,
        UUID cityId
) {
}
