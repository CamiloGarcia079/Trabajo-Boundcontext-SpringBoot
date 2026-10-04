package com.mindconnect.application.clinicalrecord.command;

import java.time.LocalDateTime;
import java.util.UUID;

public record RegisterClinicalRecordCommand(
        UUID patientId,
        LocalDateTime creationDate,
        String recordNumber,
        LocalDateTime openedAt,
        LocalDateTime closedAt,
        UUID statusId,
        UUID createdBy
) {
}
