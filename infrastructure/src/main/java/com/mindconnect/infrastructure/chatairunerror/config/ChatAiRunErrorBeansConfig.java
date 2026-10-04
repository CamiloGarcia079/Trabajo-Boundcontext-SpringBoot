package com.mindconnect.infrastructure.chatairunerror.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mindconnect.application.chatairunerror.usecase.DeleteChatAiRunErrorUseCase;
import com.mindconnect.application.chatairunerror.usecase.GetChatAiRunErrorByIdUseCase;
import com.mindconnect.application.chatairunerror.usecase.ListChatAiRunErrorUseCase;
import com.mindconnect.application.chatairunerror.usecase.RegisterChatAiRunErrorUseCase;
import com.mindconnect.application.chatairunerror.usecase.UpdateChatAiRunErrorUseCase;
import com.mindconnect.domain.chatairunerror.port.repository.ChatAiRunErrorRepository;
import com.mindconnect.infrastructure.chatairunerror.adapters.out.persistence.mappers.ChatAiRunErrorPersistenceMapper;
import com.mindconnect.infrastructure.chatairunerror.adapters.out.persistence.repositories.ChatAiRunErrorJpaRepository;
import com.mindconnect.infrastructure.chatairunerror.adapters.out.persistence.repositories.ChatAiRunErrorRepositoryAdapter;

/**
 * Conecta las piezas del contexto chatairunerror: mapper, repositorio y casos de uso.
 * Los casos de uso no usan Spring; aquí se crean y se les entrega lo que necesitan.
 */
@Configuration
public class ChatAiRunErrorBeansConfig {

    @Bean
    public ChatAiRunErrorPersistenceMapper chatAiRunErrorPersistenceMapper() {
        return new ChatAiRunErrorPersistenceMapper();
    }

    @Bean
    public ChatAiRunErrorRepository chatAiRunErrorRepository(ChatAiRunErrorJpaRepository repository, ChatAiRunErrorPersistenceMapper mapper) {
        return new ChatAiRunErrorRepositoryAdapter(repository, mapper);
    }

    @Bean
    public RegisterChatAiRunErrorUseCase registerChatAiRunErrorUseCase(ChatAiRunErrorRepository repository) {
        return new RegisterChatAiRunErrorUseCase(repository);
    }

    @Bean
    public GetChatAiRunErrorByIdUseCase getChatAiRunErrorByIdUseCase(ChatAiRunErrorRepository repository) {
        return new GetChatAiRunErrorByIdUseCase(repository);
    }

    @Bean
    public ListChatAiRunErrorUseCase listChatAiRunErrorUseCase(ChatAiRunErrorRepository repository) {
        return new ListChatAiRunErrorUseCase(repository);
    }

    @Bean
    public UpdateChatAiRunErrorUseCase updateChatAiRunErrorUseCase(ChatAiRunErrorRepository repository) {
        return new UpdateChatAiRunErrorUseCase(repository);
    }

    @Bean
    public DeleteChatAiRunErrorUseCase deleteChatAiRunErrorUseCase(ChatAiRunErrorRepository repository) {
        return new DeleteChatAiRunErrorUseCase(repository);
    }
}
