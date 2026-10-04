package com.mindconnect.application.citymunicipality.usecase;

import com.mindconnect.application.citymunicipality.dto.CityMunicipalityResponse;
import com.mindconnect.application.citymunicipality.exception.CityMunicipalityNotFoundApplicationException;
import com.mindconnect.domain.citymunicipality.model.valueobject.CityMunicipalityId;
import com.mindconnect.domain.citymunicipality.port.repository.CityMunicipalityRepository;

public class GetCityMunicipalityByIdUseCase {

    private final CityMunicipalityRepository repository;

    public GetCityMunicipalityByIdUseCase(CityMunicipalityRepository repository) {
        this.repository = repository;
    }

    public CityMunicipalityResponse execute(CityMunicipalityId id) {
        return repository.findById(id)
                .map(CityMunicipalityResponse::fromDomain)
                .orElseThrow(() -> new CityMunicipalityNotFoundApplicationException(id));
    }
}
