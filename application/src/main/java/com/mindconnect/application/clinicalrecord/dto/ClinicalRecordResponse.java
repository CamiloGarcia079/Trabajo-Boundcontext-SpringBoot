package com.mindconnect.application.clinicalrecord.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import com.mindconnect.domain.clinicalrecord.model.aggregate.ClinicalRecord;

public record ClinicalRecordResponse(
        UUID id,
        UUID patientId,
        LocalDateTime creationDate,
        String recordNumber,
        LocalDateTime openedAt,
        LocalDateTime closedAt,
        UUID statusId,
        LocalDateTime createdAt,
        UUID createdBy
) {

    public static ClinicalRecordResponse fromDomain(ClinicalRecord aggregate) {
        return new ClinicalRecordResponse(
                aggregate.id().value(),
                aggregate.patientId(), aggregate.creationDate(), aggregate.recordNumber(), aggregate.openedAt(), aggregate.closedAt(), aggregate.statusId(), aggregate.createdAt(), aggregate.createdBy());
    }
}
