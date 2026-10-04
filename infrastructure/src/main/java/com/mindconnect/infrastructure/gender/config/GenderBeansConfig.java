package com.mindconnect.infrastructure.gender.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mindconnect.application.gender.usecase.DeleteGenderUseCase;
import com.mindconnect.application.gender.usecase.GetGenderByIdUseCase;
import com.mindconnect.application.gender.usecase.ListGenderUseCase;
import com.mindconnect.application.gender.usecase.RegisterGenderUseCase;
import com.mindconnect.application.gender.usecase.UpdateGenderUseCase;
import com.mindconnect.domain.gender.port.repository.GenderRepository;
import com.mindconnect.infrastructure.gender.adapters.out.persistence.mappers.GenderPersistenceMapper;
import com.mindconnect.infrastructure.gender.adapters.out.persistence.repositories.GenderJpaRepository;
import com.mindconnect.infrastructure.gender.adapters.out.persistence.repositories.GenderRepositoryAdapter;

/**
 * Conecta las piezas del contexto gender: mapper, repositorio y casos de uso.
 * Los casos de uso no usan Spring; aquí se crean y se les entrega lo que necesitan.
 */
@Configuration
public class GenderBeansConfig {

    @Bean
    public GenderPersistenceMapper genderPersistenceMapper() {
        return new GenderPersistenceMapper();
    }

    @Bean
    public GenderRepository genderRepository(GenderJpaRepository repository, GenderPersistenceMapper mapper) {
        return new GenderRepositoryAdapter(repository, mapper);
    }

    @Bean
    public RegisterGenderUseCase registerGenderUseCase(GenderRepository repository) {
        return new RegisterGenderUseCase(repository);
    }

    @Bean
    public GetGenderByIdUseCase getGenderByIdUseCase(GenderRepository repository) {
        return new GetGenderByIdUseCase(repository);
    }

    @Bean
    public ListGenderUseCase listGenderUseCase(GenderRepository repository) {
        return new ListGenderUseCase(repository);
    }

    @Bean
    public UpdateGenderUseCase updateGenderUseCase(GenderRepository repository) {
        return new UpdateGenderUseCase(repository);
    }

    @Bean
    public DeleteGenderUseCase deleteGenderUseCase(GenderRepository repository) {
        return new DeleteGenderUseCase(repository);
    }
}
