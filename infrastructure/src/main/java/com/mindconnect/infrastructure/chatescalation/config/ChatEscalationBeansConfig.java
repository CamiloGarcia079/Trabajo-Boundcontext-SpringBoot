package com.mindconnect.infrastructure.chatescalation.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mindconnect.application.chatescalation.usecase.DeleteChatEscalationUseCase;
import com.mindconnect.application.chatescalation.usecase.GetChatEscalationByIdUseCase;
import com.mindconnect.application.chatescalation.usecase.ListChatEscalationUseCase;
import com.mindconnect.application.chatescalation.usecase.RegisterChatEscalationUseCase;
import com.mindconnect.application.chatescalation.usecase.UpdateChatEscalationUseCase;
import com.mindconnect.domain.chatescalation.port.repository.ChatEscalationRepository;
import com.mindconnect.infrastructure.chatescalation.adapters.out.persistence.mappers.ChatEscalationPersistenceMapper;
import com.mindconnect.infrastructure.chatescalation.adapters.out.persistence.repositories.ChatEscalationJpaRepository;
import com.mindconnect.infrastructure.chatescalation.adapters.out.persistence.repositories.ChatEscalationRepositoryAdapter;

/**
 * Conecta las piezas del contexto chatescalation: mapper, repositorio y casos de uso.
 * Los casos de uso no usan Spring; aquí se crean y se les entrega lo que necesitan.
 */
@Configuration
public class ChatEscalationBeansConfig {

    @Bean
    public ChatEscalationPersistenceMapper chatEscalationPersistenceMapper() {
        return new ChatEscalationPersistenceMapper();
    }

    @Bean
    public ChatEscalationRepository chatEscalationRepository(ChatEscalationJpaRepository repository, ChatEscalationPersistenceMapper mapper) {
        return new ChatEscalationRepositoryAdapter(repository, mapper);
    }

    @Bean
    public RegisterChatEscalationUseCase registerChatEscalationUseCase(ChatEscalationRepository repository) {
        return new RegisterChatEscalationUseCase(repository);
    }

    @Bean
    public GetChatEscalationByIdUseCase getChatEscalationByIdUseCase(ChatEscalationRepository repository) {
        return new GetChatEscalationByIdUseCase(repository);
    }

    @Bean
    public ListChatEscalationUseCase listChatEscalationUseCase(ChatEscalationRepository repository) {
        return new ListChatEscalationUseCase(repository);
    }

    @Bean
    public UpdateChatEscalationUseCase updateChatEscalationUseCase(ChatEscalationRepository repository) {
        return new UpdateChatEscalationUseCase(repository);
    }

    @Bean
    public DeleteChatEscalationUseCase deleteChatEscalationUseCase(ChatEscalationRepository repository) {
        return new DeleteChatEscalationUseCase(repository);
    }
}
