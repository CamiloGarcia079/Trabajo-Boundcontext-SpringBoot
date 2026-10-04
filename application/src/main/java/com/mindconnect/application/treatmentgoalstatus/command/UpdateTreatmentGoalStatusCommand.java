package com.mindconnect.application.treatmentgoalstatus.command;


import com.mindconnect.domain.treatmentgoalstatus.model.valueobject.TreatmentGoalStatusId;

public record UpdateTreatmentGoalStatusCommand(
        TreatmentGoalStatusId id,
        String code,
        String name
) {
}
