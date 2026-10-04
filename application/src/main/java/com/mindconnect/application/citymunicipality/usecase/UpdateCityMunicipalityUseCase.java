package com.mindconnect.application.citymunicipality.usecase;

import com.mindconnect.application.citymunicipality.command.UpdateCityMunicipalityCommand;
import com.mindconnect.application.citymunicipality.dto.CityMunicipalityResponse;
import com.mindconnect.application.citymunicipality.exception.CityMunicipalityNotFoundApplicationException;
import com.mindconnect.domain.citymunicipality.model.aggregate.CityMunicipality;
import com.mindconnect.domain.citymunicipality.port.repository.CityMunicipalityRepository;

public class UpdateCityMunicipalityUseCase {

    private final CityMunicipalityRepository repository;

    public UpdateCityMunicipalityUseCase(CityMunicipalityRepository repository) {
        this.repository = repository;
    }

    public CityMunicipalityResponse execute(UpdateCityMunicipalityCommand command) {
        CityMunicipality aggregate = repository.findById(command.id())
                .orElseThrow(() -> new CityMunicipalityNotFoundApplicationException(command.id()));
        aggregate.update(
                command.nameCity(), command.codeCity(), command.description(), command.regionId());
        CityMunicipality saved = repository.save(aggregate);
        return CityMunicipalityResponse.fromDomain(saved);
    }
}
