package com.mindconnect.application.encountertype.command;


import com.mindconnect.domain.encountertype.model.valueobject.EncounterTypeId;

public record UpdateEncounterTypeCommand(
        EncounterTypeId id,
        String code,
        String name
) {
}
