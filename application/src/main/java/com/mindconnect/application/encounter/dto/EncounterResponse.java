package com.mindconnect.application.encounter.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import com.mindconnect.domain.encounter.model.aggregate.Encounter;

public record EncounterResponse(
        UUID id,
        UUID clinicalRecordId,
        UUID professionalId,
        UUID encounterTypeId,
        LocalDateTime startedAt,
        LocalDateTime endedAt,
        String reasonForVisit,
        String currentCondition,
        UUID modalityId,
        UUID statusId,
        LocalDateTime createdAt,
        UUID createdBy,
        LocalDateTime updatedAt,
        UUID updatedBy
) {

    public static EncounterResponse fromDomain(Encounter aggregate) {
        return new EncounterResponse(
                aggregate.id().value(),
                aggregate.clinicalRecordId(), aggregate.professionalId(), aggregate.encounterTypeId(), aggregate.startedAt(), aggregate.endedAt(), aggregate.reasonForVisit(), aggregate.currentCondition(), aggregate.modalityId(), aggregate.statusId(), aggregate.createdAt(), aggregate.createdBy(), aggregate.updatedAt(), aggregate.updatedBy());
    }
}
