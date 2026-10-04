package com.mindconnect.application.clinicalrecordstatus.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import com.mindconnect.domain.clinicalrecordstatus.model.aggregate.ClinicalRecordStatus;

public record ClinicalRecordStatusResponse(
        UUID id,
        String code,
        String name,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {

    public static ClinicalRecordStatusResponse fromDomain(ClinicalRecordStatus aggregate) {
        return new ClinicalRecordStatusResponse(
                aggregate.id().value(),
                aggregate.code(), aggregate.name(), aggregate.createdAt(), aggregate.updatedAt());
    }
}
