package com.mindconnect.infrastructure.citymunicipality.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mindconnect.application.citymunicipality.usecase.DeleteCityMunicipalityUseCase;
import com.mindconnect.application.citymunicipality.usecase.GetCityMunicipalityByIdUseCase;
import com.mindconnect.application.citymunicipality.usecase.ListCityMunicipalityUseCase;
import com.mindconnect.application.citymunicipality.usecase.RegisterCityMunicipalityUseCase;
import com.mindconnect.application.citymunicipality.usecase.UpdateCityMunicipalityUseCase;
import com.mindconnect.domain.citymunicipality.port.repository.CityMunicipalityRepository;
import com.mindconnect.infrastructure.citymunicipality.adapters.out.persistence.mappers.CityMunicipalityPersistenceMapper;
import com.mindconnect.infrastructure.citymunicipality.adapters.out.persistence.repositories.CityMunicipalityJpaRepository;
import com.mindconnect.infrastructure.citymunicipality.adapters.out.persistence.repositories.CityMunicipalityRepositoryAdapter;

/**
 * Conecta las piezas del contexto citymunicipality: mapper, repositorio y casos de uso.
 * Los casos de uso no usan Spring; aquí se crean y se les entrega lo que necesitan.
 */
@Configuration
public class CityMunicipalityBeansConfig {

    @Bean
    public CityMunicipalityPersistenceMapper cityMunicipalityPersistenceMapper() {
        return new CityMunicipalityPersistenceMapper();
    }

    @Bean
    public CityMunicipalityRepository cityMunicipalityRepository(CityMunicipalityJpaRepository repository, CityMunicipalityPersistenceMapper mapper) {
        return new CityMunicipalityRepositoryAdapter(repository, mapper);
    }

    @Bean
    public RegisterCityMunicipalityUseCase registerCityMunicipalityUseCase(CityMunicipalityRepository repository) {
        return new RegisterCityMunicipalityUseCase(repository);
    }

    @Bean
    public GetCityMunicipalityByIdUseCase getCityMunicipalityByIdUseCase(CityMunicipalityRepository repository) {
        return new GetCityMunicipalityByIdUseCase(repository);
    }

    @Bean
    public ListCityMunicipalityUseCase listCityMunicipalityUseCase(CityMunicipalityRepository repository) {
        return new ListCityMunicipalityUseCase(repository);
    }

    @Bean
    public UpdateCityMunicipalityUseCase updateCityMunicipalityUseCase(CityMunicipalityRepository repository) {
        return new UpdateCityMunicipalityUseCase(repository);
    }

    @Bean
    public DeleteCityMunicipalityUseCase deleteCityMunicipalityUseCase(CityMunicipalityRepository repository) {
        return new DeleteCityMunicipalityUseCase(repository);
    }
}
