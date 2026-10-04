package com.mindconnect.application.stateregion.command;

import java.util.UUID;

public record RegisterStateRegionCommand(
        String nameRegion,
        String codeRegion,
        String description,
        UUID countryId
) {
}
