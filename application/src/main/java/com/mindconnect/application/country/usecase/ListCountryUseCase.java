package com.mindconnect.application.country.usecase;

import java.util.List;

import com.mindconnect.application.country.dto.CountryResponse;
import com.mindconnect.domain.country.port.repository.CountryRepository;

public class ListCountryUseCase {

    private final CountryRepository repository;

    public ListCountryUseCase(CountryRepository repository) {
        this.repository = repository;
    }

    public List<CountryResponse> execute() {
        return repository.findAll()
                .stream()
                .map(CountryResponse::fromDomain)
                .toList();
    }
}
