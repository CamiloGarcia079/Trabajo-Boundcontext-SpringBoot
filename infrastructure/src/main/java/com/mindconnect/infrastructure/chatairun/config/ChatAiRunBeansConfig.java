package com.mindconnect.infrastructure.chatairun.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mindconnect.application.chatairun.usecase.DeleteChatAiRunUseCase;
import com.mindconnect.application.chatairun.usecase.GetChatAiRunByIdUseCase;
import com.mindconnect.application.chatairun.usecase.ListChatAiRunUseCase;
import com.mindconnect.application.chatairun.usecase.RegisterChatAiRunUseCase;
import com.mindconnect.application.chatairun.usecase.UpdateChatAiRunUseCase;
import com.mindconnect.domain.chatairun.port.repository.ChatAiRunRepository;
import com.mindconnect.infrastructure.chatairun.adapters.out.persistence.mappers.ChatAiRunPersistenceMapper;
import com.mindconnect.infrastructure.chatairun.adapters.out.persistence.repositories.ChatAiRunJpaRepository;
import com.mindconnect.infrastructure.chatairun.adapters.out.persistence.repositories.ChatAiRunRepositoryAdapter;

/**
 * Conecta las piezas del contexto chatairun: mapper, repositorio y casos de uso.
 * Los casos de uso no usan Spring; aquí se crean y se les entrega lo que necesitan.
 */
@Configuration
public class ChatAiRunBeansConfig {

    @Bean
    public ChatAiRunPersistenceMapper chatAiRunPersistenceMapper() {
        return new ChatAiRunPersistenceMapper();
    }

    @Bean
    public ChatAiRunRepository chatAiRunRepository(ChatAiRunJpaRepository repository, ChatAiRunPersistenceMapper mapper) {
        return new ChatAiRunRepositoryAdapter(repository, mapper);
    }

    @Bean
    public RegisterChatAiRunUseCase registerChatAiRunUseCase(ChatAiRunRepository repository) {
        return new RegisterChatAiRunUseCase(repository);
    }

    @Bean
    public GetChatAiRunByIdUseCase getChatAiRunByIdUseCase(ChatAiRunRepository repository) {
        return new GetChatAiRunByIdUseCase(repository);
    }

    @Bean
    public ListChatAiRunUseCase listChatAiRunUseCase(ChatAiRunRepository repository) {
        return new ListChatAiRunUseCase(repository);
    }

    @Bean
    public UpdateChatAiRunUseCase updateChatAiRunUseCase(ChatAiRunRepository repository) {
        return new UpdateChatAiRunUseCase(repository);
    }

    @Bean
    public DeleteChatAiRunUseCase deleteChatAiRunUseCase(ChatAiRunRepository repository) {
        return new DeleteChatAiRunUseCase(repository);
    }
}
