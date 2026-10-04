package com.mindconnect.application.treatmentplan.command;

import java.time.LocalDate;
import java.util.UUID;

public record RegisterTreatmentPlanCommand(
        UUID encounterId,
        UUID professionalId,
        String title,
        String description,
        LocalDate startDate,
        LocalDate endDate,
        UUID treatmentStatusId
) {
}
