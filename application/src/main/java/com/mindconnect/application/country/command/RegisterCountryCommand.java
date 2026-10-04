package com.mindconnect.application.country.command;


public record RegisterCountryCommand(
        String nameCountry,
        String codeCountry,
        String description,
        String telephonePrefix
) {
}
