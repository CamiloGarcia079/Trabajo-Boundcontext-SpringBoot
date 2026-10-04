package com.mindconnect.infrastructure.priority.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mindconnect.application.priority.usecase.DeletePriorityUseCase;
import com.mindconnect.application.priority.usecase.GetPriorityByIdUseCase;
import com.mindconnect.application.priority.usecase.ListPriorityUseCase;
import com.mindconnect.application.priority.usecase.RegisterPriorityUseCase;
import com.mindconnect.application.priority.usecase.UpdatePriorityUseCase;
import com.mindconnect.domain.priority.port.repository.PriorityRepository;
import com.mindconnect.infrastructure.priority.adapters.out.persistence.mappers.PriorityPersistenceMapper;
import com.mindconnect.infrastructure.priority.adapters.out.persistence.repositories.PriorityJpaRepository;
import com.mindconnect.infrastructure.priority.adapters.out.persistence.repositories.PriorityRepositoryAdapter;

/**
 * Conecta las piezas del contexto priority: mapper, repositorio y casos de uso.
 * Los casos de uso no usan Spring; aquí se crean y se les entrega lo que necesitan.
 */
@Configuration
public class PriorityBeansConfig {

    @Bean
    public PriorityPersistenceMapper priorityPersistenceMapper() {
        return new PriorityPersistenceMapper();
    }

    @Bean
    public PriorityRepository priorityRepository(PriorityJpaRepository repository, PriorityPersistenceMapper mapper) {
        return new PriorityRepositoryAdapter(repository, mapper);
    }

    @Bean
    public RegisterPriorityUseCase registerPriorityUseCase(PriorityRepository repository) {
        return new RegisterPriorityUseCase(repository);
    }

    @Bean
    public GetPriorityByIdUseCase getPriorityByIdUseCase(PriorityRepository repository) {
        return new GetPriorityByIdUseCase(repository);
    }

    @Bean
    public ListPriorityUseCase listPriorityUseCase(PriorityRepository repository) {
        return new ListPriorityUseCase(repository);
    }

    @Bean
    public UpdatePriorityUseCase updatePriorityUseCase(PriorityRepository repository) {
        return new UpdatePriorityUseCase(repository);
    }

    @Bean
    public DeletePriorityUseCase deletePriorityUseCase(PriorityRepository repository) {
        return new DeletePriorityUseCase(repository);
    }
}
