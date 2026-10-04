package com.mindconnect.application.country.usecase;

import com.mindconnect.application.country.dto.CountryResponse;
import com.mindconnect.application.country.exception.CountryNotFoundApplicationException;
import com.mindconnect.domain.country.model.valueobject.CountryId;
import com.mindconnect.domain.country.port.repository.CountryRepository;

public class GetCountryByIdUseCase {

    private final CountryRepository repository;

    public GetCountryByIdUseCase(CountryRepository repository) {
        this.repository = repository;
    }

    public CountryResponse execute(CountryId id) {
        return repository.findById(id)
                .map(CountryResponse::fromDomain)
                .orElseThrow(() -> new CountryNotFoundApplicationException(id));
    }
}
