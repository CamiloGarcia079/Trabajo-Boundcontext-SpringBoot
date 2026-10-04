package com.mindconnect.application.country.usecase;

import com.mindconnect.application.country.command.UpdateCountryCommand;
import com.mindconnect.application.country.dto.CountryResponse;
import com.mindconnect.application.country.exception.CountryNotFoundApplicationException;
import com.mindconnect.domain.country.model.aggregate.Country;
import com.mindconnect.domain.country.port.repository.CountryRepository;

public class UpdateCountryUseCase {

    private final CountryRepository repository;

    public UpdateCountryUseCase(CountryRepository repository) {
        this.repository = repository;
    }

    public CountryResponse execute(UpdateCountryCommand command) {
        Country aggregate = repository.findById(command.id())
                .orElseThrow(() -> new CountryNotFoundApplicationException(command.id()));
        aggregate.update(
                command.nameCountry(), command.codeCountry(), command.description(), command.telephonePrefix());
        Country saved = repository.save(aggregate);
        return CountryResponse.fromDomain(saved);
    }
}
