package com.mindconnect.application.patientallergy.command;

import java.time.LocalDateTime;
import java.util.UUID;

import com.mindconnect.domain.patientallergy.model.valueobject.PatientAllergyId;

public record UpdatePatientAllergyCommand(
        PatientAllergyId id,
        UUID patientId,
        String substance,
        String reaction,
        String severity,
        LocalDateTime recordedAt,
        UUID recordedBy
) {
}
