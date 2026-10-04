package com.mindconnect.infrastructure.chatconversation.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mindconnect.application.chatconversation.usecase.DeleteChatConversationUseCase;
import com.mindconnect.application.chatconversation.usecase.GetChatConversationByIdUseCase;
import com.mindconnect.application.chatconversation.usecase.ListChatConversationUseCase;
import com.mindconnect.application.chatconversation.usecase.RegisterChatConversationUseCase;
import com.mindconnect.application.chatconversation.usecase.UpdateChatConversationUseCase;
import com.mindconnect.domain.chatconversation.port.repository.ChatConversationRepository;
import com.mindconnect.infrastructure.chatconversation.adapters.out.persistence.mappers.ChatConversationPersistenceMapper;
import com.mindconnect.infrastructure.chatconversation.adapters.out.persistence.repositories.ChatConversationJpaRepository;
import com.mindconnect.infrastructure.chatconversation.adapters.out.persistence.repositories.ChatConversationRepositoryAdapter;

/**
 * Conecta las piezas del contexto chatconversation: mapper, repositorio y casos de uso.
 * Los casos de uso no usan Spring; aquí se crean y se les entrega lo que necesitan.
 */
@Configuration
public class ChatConversationBeansConfig {

    @Bean
    public ChatConversationPersistenceMapper chatConversationPersistenceMapper() {
        return new ChatConversationPersistenceMapper();
    }

    @Bean
    public ChatConversationRepository chatConversationRepository(ChatConversationJpaRepository repository, ChatConversationPersistenceMapper mapper) {
        return new ChatConversationRepositoryAdapter(repository, mapper);
    }

    @Bean
    public RegisterChatConversationUseCase registerChatConversationUseCase(ChatConversationRepository repository) {
        return new RegisterChatConversationUseCase(repository);
    }

    @Bean
    public GetChatConversationByIdUseCase getChatConversationByIdUseCase(ChatConversationRepository repository) {
        return new GetChatConversationByIdUseCase(repository);
    }

    @Bean
    public ListChatConversationUseCase listChatConversationUseCase(ChatConversationRepository repository) {
        return new ListChatConversationUseCase(repository);
    }

    @Bean
    public UpdateChatConversationUseCase updateChatConversationUseCase(ChatConversationRepository repository) {
        return new UpdateChatConversationUseCase(repository);
    }

    @Bean
    public DeleteChatConversationUseCase deleteChatConversationUseCase(ChatConversationRepository repository) {
        return new DeleteChatConversationUseCase(repository);
    }
}
