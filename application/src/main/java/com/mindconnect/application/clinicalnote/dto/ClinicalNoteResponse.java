package com.mindconnect.application.clinicalnote.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import com.mindconnect.domain.clinicalnote.model.aggregate.ClinicalNote;

public record ClinicalNoteResponse(
        UUID id,
        UUID encounterId,
        UUID professionalId,
        String subjective,
        String objective,
        String assessment,
        String plan,
        String additionalNotes,
        LocalDateTime signedAt,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {

    public static ClinicalNoteResponse fromDomain(ClinicalNote aggregate) {
        return new ClinicalNoteResponse(
                aggregate.id().value(),
                aggregate.encounterId(), aggregate.professionalId(), aggregate.subjective(), aggregate.objective(), aggregate.assessment(), aggregate.plan(), aggregate.additionalNotes(), aggregate.signedAt(), aggregate.createdAt(), aggregate.updatedAt());
    }
}
