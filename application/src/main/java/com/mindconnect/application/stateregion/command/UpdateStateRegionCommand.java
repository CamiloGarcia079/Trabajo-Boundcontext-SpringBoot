package com.mindconnect.application.stateregion.command;

import java.util.UUID;

import com.mindconnect.domain.stateregion.model.valueobject.StateRegionId;

public record UpdateStateRegionCommand(
        StateRegionId id,
        String nameRegion,
        String codeRegion,
        String description,
        UUID countryId
) {
}
