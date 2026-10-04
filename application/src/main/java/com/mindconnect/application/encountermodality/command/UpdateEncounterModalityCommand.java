package com.mindconnect.application.encountermodality.command;


import com.mindconnect.domain.encountermodality.model.valueobject.EncounterModalityId;

public record UpdateEncounterModalityCommand(
        EncounterModalityId id,
        String code,
        String name
) {
}
