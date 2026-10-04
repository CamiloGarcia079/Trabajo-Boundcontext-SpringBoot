package com.mindconnect.infrastructure.providermodelai.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mindconnect.application.providermodelai.usecase.DeleteProviderModelAiUseCase;
import com.mindconnect.application.providermodelai.usecase.GetProviderModelAiByIdUseCase;
import com.mindconnect.application.providermodelai.usecase.ListProviderModelAiUseCase;
import com.mindconnect.application.providermodelai.usecase.RegisterProviderModelAiUseCase;
import com.mindconnect.application.providermodelai.usecase.UpdateProviderModelAiUseCase;
import com.mindconnect.domain.providermodelai.port.repository.ProviderModelAiRepository;
import com.mindconnect.infrastructure.providermodelai.adapters.out.persistence.mappers.ProviderModelAiPersistenceMapper;
import com.mindconnect.infrastructure.providermodelai.adapters.out.persistence.repositories.ProviderModelAiJpaRepository;
import com.mindconnect.infrastructure.providermodelai.adapters.out.persistence.repositories.ProviderModelAiRepositoryAdapter;

/**
 * Conecta las piezas del contexto providermodelai: mapper, repositorio y casos de uso.
 * Los casos de uso no usan Spring; aquí se crean y se les entrega lo que necesitan.
 */
@Configuration
public class ProviderModelAiBeansConfig {

    @Bean
    public ProviderModelAiPersistenceMapper providerModelAiPersistenceMapper() {
        return new ProviderModelAiPersistenceMapper();
    }

    @Bean
    public ProviderModelAiRepository providerModelAiRepository(ProviderModelAiJpaRepository repository, ProviderModelAiPersistenceMapper mapper) {
        return new ProviderModelAiRepositoryAdapter(repository, mapper);
    }

    @Bean
    public RegisterProviderModelAiUseCase registerProviderModelAiUseCase(ProviderModelAiRepository repository) {
        return new RegisterProviderModelAiUseCase(repository);
    }

    @Bean
    public GetProviderModelAiByIdUseCase getProviderModelAiByIdUseCase(ProviderModelAiRepository repository) {
        return new GetProviderModelAiByIdUseCase(repository);
    }

    @Bean
    public ListProviderModelAiUseCase listProviderModelAiUseCase(ProviderModelAiRepository repository) {
        return new ListProviderModelAiUseCase(repository);
    }

    @Bean
    public UpdateProviderModelAiUseCase updateProviderModelAiUseCase(ProviderModelAiRepository repository) {
        return new UpdateProviderModelAiUseCase(repository);
    }

    @Bean
    public DeleteProviderModelAiUseCase deleteProviderModelAiUseCase(ProviderModelAiRepository repository) {
        return new DeleteProviderModelAiUseCase(repository);
    }
}
