package com.mindconnect.application.country.usecase;

import com.mindconnect.application.country.command.RegisterCountryCommand;
import com.mindconnect.application.country.dto.CountryResponse;
import com.mindconnect.domain.country.model.aggregate.Country;
import com.mindconnect.domain.country.port.repository.CountryRepository;

public class RegisterCountryUseCase {

    private final CountryRepository repository;

    public RegisterCountryUseCase(CountryRepository repository) {
        this.repository = repository;
    }

    public CountryResponse execute(RegisterCountryCommand command) {
        Country aggregate = Country.register(
                command.nameCountry(), command.codeCountry(), command.description(), command.telephonePrefix());
        Country saved = repository.save(aggregate);
        return CountryResponse.fromDomain(saved);
    }
}
