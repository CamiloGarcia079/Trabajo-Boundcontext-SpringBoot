package com.mindconnect.infrastructure.encountermodality.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mindconnect.application.encountermodality.usecase.DeleteEncounterModalityUseCase;
import com.mindconnect.application.encountermodality.usecase.GetEncounterModalityByIdUseCase;
import com.mindconnect.application.encountermodality.usecase.ListEncounterModalityUseCase;
import com.mindconnect.application.encountermodality.usecase.RegisterEncounterModalityUseCase;
import com.mindconnect.application.encountermodality.usecase.UpdateEncounterModalityUseCase;
import com.mindconnect.domain.encountermodality.port.repository.EncounterModalityRepository;
import com.mindconnect.infrastructure.encountermodality.adapters.out.persistence.mappers.EncounterModalityPersistenceMapper;
import com.mindconnect.infrastructure.encountermodality.adapters.out.persistence.repositories.EncounterModalityJpaRepository;
import com.mindconnect.infrastructure.encountermodality.adapters.out.persistence.repositories.EncounterModalityRepositoryAdapter;

/**
 * Conecta las piezas del contexto encountermodality: mapper, repositorio y casos de uso.
 * Los casos de uso no usan Spring; aquí se crean y se les entrega lo que necesitan.
 */
@Configuration
public class EncounterModalityBeansConfig {

    @Bean
    public EncounterModalityPersistenceMapper encounterModalityPersistenceMapper() {
        return new EncounterModalityPersistenceMapper();
    }

    @Bean
    public EncounterModalityRepository encounterModalityRepository(EncounterModalityJpaRepository repository, EncounterModalityPersistenceMapper mapper) {
        return new EncounterModalityRepositoryAdapter(repository, mapper);
    }

    @Bean
    public RegisterEncounterModalityUseCase registerEncounterModalityUseCase(EncounterModalityRepository repository) {
        return new RegisterEncounterModalityUseCase(repository);
    }

    @Bean
    public GetEncounterModalityByIdUseCase getEncounterModalityByIdUseCase(EncounterModalityRepository repository) {
        return new GetEncounterModalityByIdUseCase(repository);
    }

    @Bean
    public ListEncounterModalityUseCase listEncounterModalityUseCase(EncounterModalityRepository repository) {
        return new ListEncounterModalityUseCase(repository);
    }

    @Bean
    public UpdateEncounterModalityUseCase updateEncounterModalityUseCase(EncounterModalityRepository repository) {
        return new UpdateEncounterModalityUseCase(repository);
    }

    @Bean
    public DeleteEncounterModalityUseCase deleteEncounterModalityUseCase(EncounterModalityRepository repository) {
        return new DeleteEncounterModalityUseCase(repository);
    }
}
