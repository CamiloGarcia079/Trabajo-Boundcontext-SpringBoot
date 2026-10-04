package com.mindconnect.application.clinicalrecord.command;

import java.time.LocalDateTime;
import java.util.UUID;

import com.mindconnect.domain.clinicalrecord.model.valueobject.ClinicalRecordId;

public record UpdateClinicalRecordCommand(
        ClinicalRecordId id,
        UUID patientId,
        LocalDateTime creationDate,
        String recordNumber,
        LocalDateTime openedAt,
        LocalDateTime closedAt,
        UUID statusId
) {
}
