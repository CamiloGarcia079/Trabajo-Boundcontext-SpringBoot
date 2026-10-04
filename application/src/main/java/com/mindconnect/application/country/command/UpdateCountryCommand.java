package com.mindconnect.application.country.command;


import com.mindconnect.domain.country.model.valueobject.CountryId;

public record UpdateCountryCommand(
        CountryId id,
        String nameCountry,
        String codeCountry,
        String description,
        String telephonePrefix
) {
}
