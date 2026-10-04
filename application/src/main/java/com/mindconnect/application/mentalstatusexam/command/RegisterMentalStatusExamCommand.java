package com.mindconnect.application.mentalstatusexam.command;

import java.util.UUID;

public record RegisterMentalStatusExamCommand(
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
        UUID createdBy
) {
}
