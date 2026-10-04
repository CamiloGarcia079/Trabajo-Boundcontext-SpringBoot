package com.mindconnect.infrastructure.stateregion.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mindconnect.application.stateregion.usecase.DeleteStateRegionUseCase;
import com.mindconnect.application.stateregion.usecase.GetStateRegionByIdUseCase;
import com.mindconnect.application.stateregion.usecase.ListStateRegionUseCase;
import com.mindconnect.application.stateregion.usecase.RegisterStateRegionUseCase;
import com.mindconnect.application.stateregion.usecase.UpdateStateRegionUseCase;
import com.mindconnect.domain.stateregion.port.repository.StateRegionRepository;
import com.mindconnect.infrastructure.stateregion.adapters.out.persistence.mappers.StateRegionPersistenceMapper;
import com.mindconnect.infrastructure.stateregion.adapters.out.persistence.repositories.StateRegionJpaRepository;
import com.mindconnect.infrastructure.stateregion.adapters.out.persistence.repositories.StateRegionRepositoryAdapter;

/**
 * Conecta las piezas del contexto stateregion: mapper, repositorio y casos de uso.
 * Los casos de uso no usan Spring; aquí se crean y se les entrega lo que necesitan.
 */
@Configuration
public class StateRegionBeansConfig {

    @Bean
    public StateRegionPersistenceMapper stateRegionPersistenceMapper() {
        return new StateRegionPersistenceMapper();
    }

    @Bean
    public StateRegionRepository stateRegionRepository(StateRegionJpaRepository repository, StateRegionPersistenceMapper mapper) {
        return new StateRegionRepositoryAdapter(repository, mapper);
    }

    @Bean
    public RegisterStateRegionUseCase registerStateRegionUseCase(StateRegionRepository repository) {
        return new RegisterStateRegionUseCase(repository);
    }

    @Bean
    public GetStateRegionByIdUseCase getStateRegionByIdUseCase(StateRegionRepository repository) {
        return new GetStateRegionByIdUseCase(repository);
    }

    @Bean
    public ListStateRegionUseCase listStateRegionUseCase(StateRegionRepository repository) {
        return new ListStateRegionUseCase(repository);
    }

    @Bean
    public UpdateStateRegionUseCase updateStateRegionUseCase(StateRegionRepository repository) {
        return new UpdateStateRegionUseCase(repository);
    }

    @Bean
    public DeleteStateRegionUseCase deleteStateRegionUseCase(StateRegionRepository repository) {
        return new DeleteStateRegionUseCase(repository);
    }
}
