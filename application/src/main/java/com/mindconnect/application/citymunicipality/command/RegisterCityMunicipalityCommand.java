package com.mindconnect.application.citymunicipality.command;

import java.util.UUID;

public record RegisterCityMunicipalityCommand(
        String nameCity,
        String codeCity,
        String description,
        UUID regionId
) {
}
