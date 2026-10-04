package com.mindconnect.application.country.usecase;

import java.time.LocalDateTime;

import com.mindconnect.application.country.exception.CountryNotFoundApplicationException;
import com.mindconnect.domain.country.event.CountryDeletedEvent;
import com.mindconnect.domain.country.model.valueobject.CountryId;
import com.mindconnect.domain.country.port.repository.CountryRepository;

public class DeleteCountryUseCase {

    private final CountryRepository repository;

    public DeleteCountryUseCase(CountryRepository repository) {
        this.repository = repository;
    }

    public CountryDeletedEvent execute(CountryId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new CountryNotFoundApplicationException(id));

        repository.delete(aggregate);

        return new CountryDeletedEvent(id, LocalDateTime.now());
    }
}
