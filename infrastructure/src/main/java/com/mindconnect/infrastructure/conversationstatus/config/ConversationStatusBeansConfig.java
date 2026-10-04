package com.mindconnect.infrastructure.conversationstatus.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mindconnect.application.conversationstatus.usecase.DeleteConversationStatusUseCase;
import com.mindconnect.application.conversationstatus.usecase.GetConversationStatusByIdUseCase;
import com.mindconnect.application.conversationstatus.usecase.ListConversationStatusUseCase;
import com.mindconnect.application.conversationstatus.usecase.RegisterConversationStatusUseCase;
import com.mindconnect.application.conversationstatus.usecase.UpdateConversationStatusUseCase;
import com.mindconnect.domain.conversationstatus.port.repository.ConversationStatusRepository;
import com.mindconnect.infrastructure.conversationstatus.adapters.out.persistence.mappers.ConversationStatusPersistenceMapper;
import com.mindconnect.infrastructure.conversationstatus.adapters.out.persistence.repositories.ConversationStatusJpaRepository;
import com.mindconnect.infrastructure.conversationstatus.adapters.out.persistence.repositories.ConversationStatusRepositoryAdapter;

/**
 * Conecta las piezas del contexto conversationstatus: mapper, repositorio y casos de uso.
 * Los casos de uso no usan Spring; aquí se crean y se les entrega lo que necesitan.
 */
@Configuration
public class ConversationStatusBeansConfig {

    @Bean
    public ConversationStatusPersistenceMapper conversationStatusPersistenceMapper() {
        return new ConversationStatusPersistenceMapper();
    }

    @Bean
    public ConversationStatusRepository conversationStatusRepository(ConversationStatusJpaRepository repository, ConversationStatusPersistenceMapper mapper) {
        return new ConversationStatusRepositoryAdapter(repository, mapper);
    }

    @Bean
    public RegisterConversationStatusUseCase registerConversationStatusUseCase(ConversationStatusRepository repository) {
        return new RegisterConversationStatusUseCase(repository);
    }

    @Bean
    public GetConversationStatusByIdUseCase getConversationStatusByIdUseCase(ConversationStatusRepository repository) {
        return new GetConversationStatusByIdUseCase(repository);
    }

    @Bean
    public ListConversationStatusUseCase listConversationStatusUseCase(ConversationStatusRepository repository) {
        return new ListConversationStatusUseCase(repository);
    }

    @Bean
    public UpdateConversationStatusUseCase updateConversationStatusUseCase(ConversationStatusRepository repository) {
        return new UpdateConversationStatusUseCase(repository);
    }

    @Bean
    public DeleteConversationStatusUseCase deleteConversationStatusUseCase(ConversationStatusRepository repository) {
        return new DeleteConversationStatusUseCase(repository);
    }
}
