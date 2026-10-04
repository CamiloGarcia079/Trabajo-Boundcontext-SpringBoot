package com.mindconnect.application.encounter.command;

import java.time.LocalDateTime;
import java.util.UUID;

public record RegisterEncounterCommand(
        UUID clinicalRecordId,
        UUID professionalId,
        UUID encounterTypeId,
        LocalDateTime startedAt,
        LocalDateTime endedAt,
        String reasonForVisit,
        String currentCondition,
        UUID modalityId,
        UUID statusId,
        UUID createdBy,
        UUID updatedBy
) {
}
