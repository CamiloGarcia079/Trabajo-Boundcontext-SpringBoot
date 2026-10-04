package com.mindconnect.application.citymunicipality.usecase;

import java.time.LocalDateTime;

import com.mindconnect.application.citymunicipality.exception.CityMunicipalityNotFoundApplicationException;
import com.mindconnect.domain.citymunicipality.event.CityMunicipalityDeletedEvent;
import com.mindconnect.domain.citymunicipality.model.valueobject.CityMunicipalityId;
import com.mindconnect.domain.citymunicipality.port.repository.CityMunicipalityRepository;

public class DeleteCityMunicipalityUseCase {

    private final CityMunicipalityRepository repository;

    public DeleteCityMunicipalityUseCase(CityMunicipalityRepository repository) {
        this.repository = repository;
    }

    public CityMunicipalityDeletedEvent execute(CityMunicipalityId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new CityMunicipalityNotFoundApplicationException(id));

        repository.delete(aggregate);

        return new CityMunicipalityDeletedEvent(id, LocalDateTime.now());
    }
}
