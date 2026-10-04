package com.mindconnect.application.mentalstatusexam.command;

import java.util.UUID;

import com.mindconnect.domain.mentalstatusexam.model.valueobject.MentalStatusExamId;

public record UpdateMentalStatusExamCommand(
        MentalStatusExamId id,
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
        String observations
) {
}
