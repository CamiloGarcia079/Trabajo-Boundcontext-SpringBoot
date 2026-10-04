package com.mindconnect.application.encountertype.command;


public record RegisterEncounterTypeCommand(
        String code,
        String name
) {
}
