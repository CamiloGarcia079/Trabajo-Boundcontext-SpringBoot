package com.mindconnect.infrastructure.chatparticipant.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mindconnect.application.chatparticipant.usecase.DeleteChatParticipantUseCase;
import com.mindconnect.application.chatparticipant.usecase.GetChatParticipantByIdUseCase;
import com.mindconnect.application.chatparticipant.usecase.ListChatParticipantUseCase;
import com.mindconnect.application.chatparticipant.usecase.RegisterChatParticipantUseCase;
import com.mindconnect.application.chatparticipant.usecase.UpdateChatParticipantUseCase;
import com.mindconnect.domain.chatparticipant.port.repository.ChatParticipantRepository;
import com.mindconnect.infrastructure.chatparticipant.adapters.out.persistence.mappers.ChatParticipantPersistenceMapper;
import com.mindconnect.infrastructure.chatparticipant.adapters.out.persistence.repositories.ChatParticipantJpaRepository;
import com.mindconnect.infrastructure.chatparticipant.adapters.out.persistence.repositories.ChatParticipantRepositoryAdapter;

/**
 * Conecta las piezas del contexto chatparticipant: mapper, repositorio y casos de uso.
 * Los casos de uso no usan Spring; aquí se crean y se les entrega lo que necesitan.
 */
@Configuration
public class ChatParticipantBeansConfig {

    @Bean
    public ChatParticipantPersistenceMapper chatParticipantPersistenceMapper() {
        return new ChatParticipantPersistenceMapper();
    }

    @Bean
    public ChatParticipantRepository chatParticipantRepository(ChatParticipantJpaRepository repository, ChatParticipantPersistenceMapper mapper) {
        return new ChatParticipantRepositoryAdapter(repository, mapper);
    }

    @Bean
    public RegisterChatParticipantUseCase registerChatParticipantUseCase(ChatParticipantRepository repository) {
        return new RegisterChatParticipantUseCase(repository);
    }

    @Bean
    public GetChatParticipantByIdUseCase getChatParticipantByIdUseCase(ChatParticipantRepository repository) {
        return new GetChatParticipantByIdUseCase(repository);
    }

    @Bean
    public ListChatParticipantUseCase listChatParticipantUseCase(ChatParticipantRepository repository) {
        return new ListChatParticipantUseCase(repository);
    }

    @Bean
    public UpdateChatParticipantUseCase updateChatParticipantUseCase(ChatParticipantRepository repository) {
        return new UpdateChatParticipantUseCase(repository);
    }

    @Bean
    public DeleteChatParticipantUseCase deleteChatParticipantUseCase(ChatParticipantRepository repository) {
        return new DeleteChatParticipantUseCase(repository);
    }
}
