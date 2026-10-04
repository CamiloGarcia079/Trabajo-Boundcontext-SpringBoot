package com.mindconnect.infrastructure.chatescalationstatushistory.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mindconnect.application.chatescalationstatushistory.usecase.DeleteChatEscalationStatusHistoryUseCase;
import com.mindconnect.application.chatescalationstatushistory.usecase.GetChatEscalationStatusHistoryByIdUseCase;
import com.mindconnect.application.chatescalationstatushistory.usecase.ListChatEscalationStatusHistoryUseCase;
import com.mindconnect.application.chatescalationstatushistory.usecase.RegisterChatEscalationStatusHistoryUseCase;
import com.mindconnect.application.chatescalationstatushistory.usecase.UpdateChatEscalationStatusHistoryUseCase;
import com.mindconnect.domain.chatescalationstatushistory.port.repository.ChatEscalationStatusHistoryRepository;
import com.mindconnect.infrastructure.chatescalationstatushistory.adapters.out.persistence.mappers.ChatEscalationStatusHistoryPersistenceMapper;
import com.mindconnect.infrastructure.chatescalationstatushistory.adapters.out.persistence.repositories.ChatEscalationStatusHistoryJpaRepository;
import com.mindconnect.infrastructure.chatescalationstatushistory.adapters.out.persistence.repositories.ChatEscalationStatusHistoryRepositoryAdapter;

/**
 * Conecta las piezas del contexto chatescalationstatushistory: mapper, repositorio y casos de uso.
 * Los casos de uso no usan Spring; aquí se crean y se les entrega lo que necesitan.
 */
@Configuration
public class ChatEscalationStatusHistoryBeansConfig {

    @Bean
    public ChatEscalationStatusHistoryPersistenceMapper chatEscalationStatusHistoryPersistenceMapper() {
        return new ChatEscalationStatusHistoryPersistenceMapper();
    }

    @Bean
    public ChatEscalationStatusHistoryRepository chatEscalationStatusHistoryRepository(ChatEscalationStatusHistoryJpaRepository repository, ChatEscalationStatusHistoryPersistenceMapper mapper) {
        return new ChatEscalationStatusHistoryRepositoryAdapter(repository, mapper);
    }

    @Bean
    public RegisterChatEscalationStatusHistoryUseCase registerChatEscalationStatusHistoryUseCase(ChatEscalationStatusHistoryRepository repository) {
        return new RegisterChatEscalationStatusHistoryUseCase(repository);
    }

    @Bean
    public GetChatEscalationStatusHistoryByIdUseCase getChatEscalationStatusHistoryByIdUseCase(ChatEscalationStatusHistoryRepository repository) {
        return new GetChatEscalationStatusHistoryByIdUseCase(repository);
    }

    @Bean
    public ListChatEscalationStatusHistoryUseCase listChatEscalationStatusHistoryUseCase(ChatEscalationStatusHistoryRepository repository) {
        return new ListChatEscalationStatusHistoryUseCase(repository);
    }

    @Bean
    public UpdateChatEscalationStatusHistoryUseCase updateChatEscalationStatusHistoryUseCase(ChatEscalationStatusHistoryRepository repository) {
        return new UpdateChatEscalationStatusHistoryUseCase(repository);
    }

    @Bean
    public DeleteChatEscalationStatusHistoryUseCase deleteChatEscalationStatusHistoryUseCase(ChatEscalationStatusHistoryRepository repository) {
        return new DeleteChatEscalationStatusHistoryUseCase(repository);
    }
}
