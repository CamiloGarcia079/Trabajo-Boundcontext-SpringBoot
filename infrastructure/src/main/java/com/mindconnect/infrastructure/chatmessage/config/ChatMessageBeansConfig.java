package com.mindconnect.infrastructure.chatmessage.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mindconnect.application.chatmessage.usecase.DeleteChatMessageUseCase;
import com.mindconnect.application.chatmessage.usecase.GetChatMessageByIdUseCase;
import com.mindconnect.application.chatmessage.usecase.ListChatMessageUseCase;
import com.mindconnect.application.chatmessage.usecase.RegisterChatMessageUseCase;
import com.mindconnect.application.chatmessage.usecase.UpdateChatMessageUseCase;
import com.mindconnect.domain.chatmessage.port.repository.ChatMessageRepository;
import com.mindconnect.infrastructure.chatmessage.adapters.out.persistence.mappers.ChatMessagePersistenceMapper;
import com.mindconnect.infrastructure.chatmessage.adapters.out.persistence.repositories.ChatMessageJpaRepository;
import com.mindconnect.infrastructure.chatmessage.adapters.out.persistence.repositories.ChatMessageRepositoryAdapter;

/**
 * Conecta las piezas del contexto chatmessage: mapper, repositorio y casos de uso.
 * Los casos de uso no usan Spring; aquí se crean y se les entrega lo que necesitan.
 */
@Configuration
public class ChatMessageBeansConfig {

    @Bean
    public ChatMessagePersistenceMapper chatMessagePersistenceMapper() {
        return new ChatMessagePersistenceMapper();
    }

    @Bean
    public ChatMessageRepository chatMessageRepository(ChatMessageJpaRepository repository, ChatMessagePersistenceMapper mapper) {
        return new ChatMessageRepositoryAdapter(repository, mapper);
    }

    @Bean
    public RegisterChatMessageUseCase registerChatMessageUseCase(ChatMessageRepository repository) {
        return new RegisterChatMessageUseCase(repository);
    }

    @Bean
    public GetChatMessageByIdUseCase getChatMessageByIdUseCase(ChatMessageRepository repository) {
        return new GetChatMessageByIdUseCase(repository);
    }

    @Bean
    public ListChatMessageUseCase listChatMessageUseCase(ChatMessageRepository repository) {
        return new ListChatMessageUseCase(repository);
    }

    @Bean
    public UpdateChatMessageUseCase updateChatMessageUseCase(ChatMessageRepository repository) {
        return new UpdateChatMessageUseCase(repository);
    }

    @Bean
    public DeleteChatMessageUseCase deleteChatMessageUseCase(ChatMessageRepository repository) {
        return new DeleteChatMessageUseCase(repository);
    }
}
