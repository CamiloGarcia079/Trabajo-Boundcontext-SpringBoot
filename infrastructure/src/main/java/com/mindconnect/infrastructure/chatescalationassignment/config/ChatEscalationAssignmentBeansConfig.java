package com.mindconnect.infrastructure.chatescalationassignment.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mindconnect.application.chatescalationassignment.usecase.DeleteChatEscalationAssignmentUseCase;
import com.mindconnect.application.chatescalationassignment.usecase.GetChatEscalationAssignmentByIdUseCase;
import com.mindconnect.application.chatescalationassignment.usecase.ListChatEscalationAssignmentUseCase;
import com.mindconnect.application.chatescalationassignment.usecase.RegisterChatEscalationAssignmentUseCase;
import com.mindconnect.application.chatescalationassignment.usecase.UpdateChatEscalationAssignmentUseCase;
import com.mindconnect.domain.chatescalationassignment.port.repository.ChatEscalationAssignmentRepository;
import com.mindconnect.infrastructure.chatescalationassignment.adapters.out.persistence.mappers.ChatEscalationAssignmentPersistenceMapper;
import com.mindconnect.infrastructure.chatescalationassignment.adapters.out.persistence.repositories.ChatEscalationAssignmentJpaRepository;
import com.mindconnect.infrastructure.chatescalationassignment.adapters.out.persistence.repositories.ChatEscalationAssignmentRepositoryAdapter;

/**
 * Conecta las piezas del contexto chatescalationassignment: mapper, repositorio y casos de uso.
 * Los casos de uso no usan Spring; aquí se crean y se les entrega lo que necesitan.
 */
@Configuration
public class ChatEscalationAssignmentBeansConfig {

    @Bean
    public ChatEscalationAssignmentPersistenceMapper chatEscalationAssignmentPersistenceMapper() {
        return new ChatEscalationAssignmentPersistenceMapper();
    }

    @Bean
    public ChatEscalationAssignmentRepository chatEscalationAssignmentRepository(ChatEscalationAssignmentJpaRepository repository, ChatEscalationAssignmentPersistenceMapper mapper) {
        return new ChatEscalationAssignmentRepositoryAdapter(repository, mapper);
    }

    @Bean
    public RegisterChatEscalationAssignmentUseCase registerChatEscalationAssignmentUseCase(ChatEscalationAssignmentRepository repository) {
        return new RegisterChatEscalationAssignmentUseCase(repository);
    }

    @Bean
    public GetChatEscalationAssignmentByIdUseCase getChatEscalationAssignmentByIdUseCase(ChatEscalationAssignmentRepository repository) {
        return new GetChatEscalationAssignmentByIdUseCase(repository);
    }

    @Bean
    public ListChatEscalationAssignmentUseCase listChatEscalationAssignmentUseCase(ChatEscalationAssignmentRepository repository) {
        return new ListChatEscalationAssignmentUseCase(repository);
    }

    @Bean
    public UpdateChatEscalationAssignmentUseCase updateChatEscalationAssignmentUseCase(ChatEscalationAssignmentRepository repository) {
        return new UpdateChatEscalationAssignmentUseCase(repository);
    }

    @Bean
    public DeleteChatEscalationAssignmentUseCase deleteChatEscalationAssignmentUseCase(ChatEscalationAssignmentRepository repository) {
        return new DeleteChatEscalationAssignmentUseCase(repository);
    }
}
