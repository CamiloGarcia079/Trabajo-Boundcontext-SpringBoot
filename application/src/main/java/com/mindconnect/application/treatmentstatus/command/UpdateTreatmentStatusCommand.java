package com.mindconnect.application.treatmentstatus.command;


import com.mindconnect.domain.treatmentstatus.model.valueobject.TreatmentStatusId;

public record UpdateTreatmentStatusCommand(
        TreatmentStatusId id,
        String code,
        String name
) {
}
