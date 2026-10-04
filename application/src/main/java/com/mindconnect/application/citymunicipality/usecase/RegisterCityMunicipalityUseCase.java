package com.mindconnect.application.citymunicipality.usecase;

import com.mindconnect.application.citymunicipality.command.RegisterCityMunicipalityCommand;
import com.mindconnect.application.citymunicipality.dto.CityMunicipalityResponse;
import com.mindconnect.domain.citymunicipality.model.aggregate.CityMunicipality;
import com.mindconnect.domain.citymunicipality.port.repository.CityMunicipalityRepository;

public class RegisterCityMunicipalityUseCase {

    private final CityMunicipalityRepository repository;

    public RegisterCityMunicipalityUseCase(CityMunicipalityRepository repository) {
        this.repository = repository;
    }

    public CityMunicipalityResponse execute(RegisterCityMunicipalityCommand command) {
        CityMunicipality aggregate = CityMunicipality.register(
                command.nameCity(), command.codeCity(), command.description(), command.regionId());
        CityMunicipality saved = repository.save(aggregate);
        return CityMunicipalityResponse.fromDomain(saved);
    }
}
