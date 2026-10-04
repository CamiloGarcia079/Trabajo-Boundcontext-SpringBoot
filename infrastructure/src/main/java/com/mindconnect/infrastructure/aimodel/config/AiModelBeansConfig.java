package com.mindconnect.infrastructure.aimodel.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mindconnect.application.aimodel.usecase.DeleteAiModelUseCase;
import com.mindconnect.application.aimodel.usecase.GetAiModelByIdUseCase;
import com.mindconnect.application.aimodel.usecase.ListAiModelUseCase;
import com.mindconnect.application.aimodel.usecase.RegisterAiModelUseCase;
import com.mindconnect.application.aimodel.usecase.UpdateAiModelUseCase;
import com.mindconnect.domain.aimodel.port.repository.AiModelRepository;
import com.mindconnect.infrastructure.aimodel.adapters.out.persistence.mappers.AiModelPersistenceMapper;
import com.mindconnect.infrastructure.aimodel.adapters.out.persistence.repositories.AiModelJpaRepository;
import com.mindconnect.infrastructure.aimodel.adapters.out.persistence.repositories.AiModelRepositoryAdapter;

/**
 * Conecta las piezas del contexto aimodel: mapper, repositorio y casos de uso.
 * Los casos de uso no usan Spring; aquí se crean y se les entrega lo que necesitan.
 */
@Configuration
public class AiModelBeansConfig {

    @Bean
    public AiModelPersistenceMapper aiModelPersistenceMapper() {
        return new AiModelPersistenceMapper();
    }

    @Bean
    public AiModelRepository aiModelRepository(AiModelJpaRepository repository, AiModelPersistenceMapper mapper) {
        return new AiModelRepositoryAdapter(repository, mapper);
    }

    @Bean
    public RegisterAiModelUseCase registerAiModelUseCase(AiModelRepository repository) {
        return new RegisterAiModelUseCase(repository);
    }

    @Bean
    public GetAiModelByIdUseCase getAiModelByIdUseCase(AiModelRepository repository) {
        return new GetAiModelByIdUseCase(repository);
    }

    @Bean
    public ListAiModelUseCase listAiModelUseCase(AiModelRepository repository) {
        return new ListAiModelUseCase(repository);
    }

    @Bean
    public UpdateAiModelUseCase updateAiModelUseCase(AiModelRepository repository) {
        return new UpdateAiModelUseCase(repository);
    }

    @Bean
    public DeleteAiModelUseCase deleteAiModelUseCase(AiModelRepository repository) {
        return new DeleteAiModelUseCase(repository);
    }
}
