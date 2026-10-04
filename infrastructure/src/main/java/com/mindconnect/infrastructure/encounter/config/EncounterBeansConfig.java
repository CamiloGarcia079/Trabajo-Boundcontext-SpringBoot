package com.mindconnect.infrastructure.encounter.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mindconnect.application.encounter.usecase.DeleteEncounterUseCase;
import com.mindconnect.application.encounter.usecase.GetEncounterByIdUseCase;
import com.mindconnect.application.encounter.usecase.ListEncounterUseCase;
import com.mindconnect.application.encounter.usecase.RegisterEncounterUseCase;
import com.mindconnect.application.encounter.usecase.UpdateEncounterUseCase;
import com.mindconnect.domain.encounter.port.repository.EncounterRepository;
import com.mindconnect.infrastructure.encounter.adapters.out.persistence.mappers.EncounterPersistenceMapper;
import com.mindconnect.infrastructure.encounter.adapters.out.persistence.repositories.EncounterJpaRepository;
import com.mindconnect.infrastructure.encounter.adapters.out.persistence.repositories.EncounterRepositoryAdapter;

/**
 * Conecta las piezas del contexto encounter: mapper, repositorio y casos de uso.
 * Los casos de uso no usan Spring; aquí se crean y se les entrega lo que necesitan.
 */
@Configuration
public class EncounterBeansConfig {

    @Bean
    public EncounterPersistenceMapper encounterPersistenceMapper() {
        return new EncounterPersistenceMapper();
    }

    @Bean
    public EncounterRepository encounterRepository(EncounterJpaRepository repository, EncounterPersistenceMapper mapper) {
        return new EncounterRepositoryAdapter(repository, mapper);
    }

    @Bean
    public RegisterEncounterUseCase registerEncounterUseCase(EncounterRepository repository) {
        return new RegisterEncounterUseCase(repository);
    }

    @Bean
    public GetEncounterByIdUseCase getEncounterByIdUseCase(EncounterRepository repository) {
        return new GetEncounterByIdUseCase(repository);
    }

    @Bean
    public ListEncounterUseCase listEncounterUseCase(EncounterRepository repository) {
        return new ListEncounterUseCase(repository);
    }

    @Bean
    public UpdateEncounterUseCase updateEncounterUseCase(EncounterRepository repository) {
        return new UpdateEncounterUseCase(repository);
    }

    @Bean
    public DeleteEncounterUseCase deleteEncounterUseCase(EncounterRepository repository) {
        return new DeleteEncounterUseCase(repository);
    }
}
