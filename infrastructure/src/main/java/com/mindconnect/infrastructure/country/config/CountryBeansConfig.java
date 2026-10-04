package com.mindconnect.infrastructure.country.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mindconnect.application.country.usecase.DeleteCountryUseCase;
import com.mindconnect.application.country.usecase.GetCountryByIdUseCase;
import com.mindconnect.application.country.usecase.ListCountryUseCase;
import com.mindconnect.application.country.usecase.RegisterCountryUseCase;
import com.mindconnect.application.country.usecase.UpdateCountryUseCase;
import com.mindconnect.domain.country.port.repository.CountryRepository;
import com.mindconnect.infrastructure.country.adapters.out.persistence.mappers.CountryPersistenceMapper;
import com.mindconnect.infrastructure.country.adapters.out.persistence.repositories.CountryJpaRepository;
import com.mindconnect.infrastructure.country.adapters.out.persistence.repositories.CountryRepositoryAdapter;

/**
 * Conecta las piezas del contexto country: mapper, repositorio y casos de uso.
 * Los casos de uso no usan Spring; aquí se crean y se les entrega lo que necesitan.
 */
@Configuration
public class CountryBeansConfig {

    @Bean
    public CountryPersistenceMapper countryPersistenceMapper() {
        return new CountryPersistenceMapper();
    }

    @Bean
    public CountryRepository countryRepository(CountryJpaRepository repository, CountryPersistenceMapper mapper) {
        return new CountryRepositoryAdapter(repository, mapper);
    }

    @Bean
    public RegisterCountryUseCase registerCountryUseCase(CountryRepository repository) {
        return new RegisterCountryUseCase(repository);
    }

    @Bean
    public GetCountryByIdUseCase getCountryByIdUseCase(CountryRepository repository) {
        return new GetCountryByIdUseCase(repository);
    }

    @Bean
    public ListCountryUseCase listCountryUseCase(CountryRepository repository) {
        return new ListCountryUseCase(repository);
    }

    @Bean
    public UpdateCountryUseCase updateCountryUseCase(CountryRepository repository) {
        return new UpdateCountryUseCase(repository);
    }

    @Bean
    public DeleteCountryUseCase deleteCountryUseCase(CountryRepository repository) {
        return new DeleteCountryUseCase(repository);
    }
}
