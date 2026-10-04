package com.mindconnect.application.encounterstatus.command;


import com.mindconnect.domain.encounterstatus.model.valueobject.EncounterStatusId;

public record UpdateEncounterStatusCommand(
        EncounterStatusId id,
        String code,
        String name
) {
}
