package com.mindconnect.application.mentalstatusexam.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import com.mindconnect.domain.mentalstatusexam.model.aggregate.MentalStatusExam;

public record MentalStatusExamResponse(
        UUID id,
        UUID encounterId,
        String appearance,
        String behavior,
        String attitude,
        String consciousness,
        String orientation,
        String attention,
        String memory,
        String speech,
        String mood,
        String affect,
        String thoughtProcess,
        String thoughtContent,
        String perception,
        String judgment,
        String insight,
        String psychomotorActivity,
        String observations,
        LocalDateTime createdAt,
        UUID createdBy
) {

    public static MentalStatusExamResponse fromDomain(MentalStatusExam aggregate) {
        return new MentalStatusExamResponse(
                aggregate.id().value(),
                aggregate.encounterId(), aggregate.appearance(), aggregate.behavior(), aggregate.attitude(), aggregate.consciousness(), aggregate.orientation(), aggregate.attention(), aggregate.memory(), aggregate.speech(), aggregate.mood(), aggregate.affect(), aggregate.thoughtProcess(), aggregate.thoughtContent(), aggregate.perception(), aggregate.judgment(), aggregate.insight(), aggregate.psychomotorActivity(), aggregate.observations(), aggregate.createdAt(), aggregate.createdBy());
    }
}
