package com.mindconnect.application.clinicalnote.command;

import java.time.LocalDateTime;
import java.util.UUID;

public record RegisterClinicalNoteCommand(
        UUID encounterId,
        UUID professionalId,
        String subjective,
        String objective,
        String assessment,
        String plan,
        String additionalNotes,
        LocalDateTime signedAt
) {
}
