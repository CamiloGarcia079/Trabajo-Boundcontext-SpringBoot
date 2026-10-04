package com.mindconnect.infrastructure.airunstatus.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mindconnect.application.airunstatus.usecase.DeleteAiRunStatusUseCase;
import com.mindconnect.application.airunstatus.usecase.GetAiRunStatusByIdUseCase;
import com.mindconnect.application.airunstatus.usecase.ListAiRunStatusUseCase;
import com.mindconnect.application.airunstatus.usecase.RegisterAiRunStatusUseCase;
import com.mindconnect.application.airunstatus.usecase.UpdateAiRunStatusUseCase;
import com.mindconnect.domain.airunstatus.port.repository.AiRunStatusRepository;
import com.mindconnect.infrastructure.airunstatus.adapters.out.persistence.mappers.AiRunStatusPersistenceMapper;
import com.mindconnect.infrastructure.airunstatus.adapters.out.persistence.repositories.AiRunStatusJpaRepository;
import com.mindconnect.infrastructure.airunstatus.adapters.out.persistence.repositories.AiRunStatusRepositoryAdapter;

/**
 * Conecta las piezas del contexto airunstatus: mapper, repositorio y casos de uso.
 * Los casos de uso no usan Spring; aquí se crean y se les entrega lo que necesitan.
 */
@Configuration
public class AiRunStatusBeansConfig {

    @Bean
    public AiRunStatusPersistenceMapper aiRunStatusPersistenceMapper() {
        return new AiRunStatusPersistenceMapper();
    }

    @Bean
    public AiRunStatusRepository aiRunStatusRepository(AiRunStatusJpaRepository repository, AiRunStatusPersistenceMapper mapper) {
        return new AiRunStatusRepositoryAdapter(repository, mapper);
    }

    @Bean
    public RegisterAiRunStatusUseCase registerAiRunStatusUseCase(AiRunStatusRepository repository) {
        return new RegisterAiRunStatusUseCase(repository);
    }

    @Bean
    public GetAiRunStatusByIdUseCase getAiRunStatusByIdUseCase(AiRunStatusRepository repository) {
        return new GetAiRunStatusByIdUseCase(repository);
    }

    @Bean
    public ListAiRunStatusUseCase listAiRunStatusUseCase(AiRunStatusRepository repository) {
        return new ListAiRunStatusUseCase(repository);
    }

    @Bean
    public UpdateAiRunStatusUseCase updateAiRunStatusUseCase(AiRunStatusRepository repository) {
        return new UpdateAiRunStatusUseCase(repository);
    }

    @Bean
    public DeleteAiRunStatusUseCase deleteAiRunStatusUseCase(AiRunStatusRepository repository) {
        return new DeleteAiRunStatusUseCase(repository);
    }
}
