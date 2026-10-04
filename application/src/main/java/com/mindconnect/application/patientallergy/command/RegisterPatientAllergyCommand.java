package com.mindconnect.application.patientallergy.command;

import java.time.LocalDateTime;
import java.util.UUID;

public record RegisterPatientAllergyCommand(
        UUID patientId,
        String substance,
        String reaction,
        String severity,
        LocalDateTime recordedAt,
        UUID recordedBy
) {
}
