package com.mindconnect.application.citymunicipality.command;

import java.util.UUID;

import com.mindconnect.domain.citymunicipality.model.valueobject.CityMunicipalityId;

public record UpdateCityMunicipalityCommand(
        CityMunicipalityId id,
        String nameCity,
        String codeCity,
        String description,
        UUID regionId
) {
}
