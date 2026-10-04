package com.mindconnect.infrastructure.chatairunmetric.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mindconnect.application.chatairunmetric.usecase.DeleteChatAiRunMetricUseCase;
import com.mindconnect.application.chatairunmetric.usecase.GetChatAiRunMetricByIdUseCase;
import com.mindconnect.application.chatairunmetric.usecase.ListChatAiRunMetricUseCase;
import com.mindconnect.application.chatairunmetric.usecase.RegisterChatAiRunMetricUseCase;
import com.mindconnect.application.chatairunmetric.usecase.UpdateChatAiRunMetricUseCase;
import com.mindconnect.domain.chatairunmetric.port.repository.ChatAiRunMetricRepository;
import com.mindconnect.infrastructure.chatairunmetric.adapters.out.persistence.mappers.ChatAiRunMetricPersistenceMapper;
import com.mindconnect.infrastructure.chatairunmetric.adapters.out.persistence.repositories.ChatAiRunMetricJpaRepository;
import com.mindconnect.infrastructure.chatairunmetric.adapters.out.persistence.repositories.ChatAiRunMetricRepositoryAdapter;

/**
 * Conecta las piezas del contexto chatairunmetric: mapper, repositorio y casos de uso.
 * Los casos de uso no usan Spring; aquí se crean y se les entrega lo que necesitan.
 */
@Configuration
public class ChatAiRunMetricBeansConfig {

    @Bean
    public ChatAiRunMetricPersistenceMapper chatAiRunMetricPersistenceMapper() {
        return new ChatAiRunMetricPersistenceMapper();
    }

    @Bean
    public ChatAiRunMetricRepository chatAiRunMetricRepository(ChatAiRunMetricJpaRepository repository, ChatAiRunMetricPersistenceMapper mapper) {
        return new ChatAiRunMetricRepositoryAdapter(repository, mapper);
    }

    @Bean
    public RegisterChatAiRunMetricUseCase registerChatAiRunMetricUseCase(ChatAiRunMetricRepository repository) {
        return new RegisterChatAiRunMetricUseCase(repository);
    }

    @Bean
    public GetChatAiRunMetricByIdUseCase getChatAiRunMetricByIdUseCase(ChatAiRunMetricRepository repository) {
        return new GetChatAiRunMetricByIdUseCase(repository);
    }

    @Bean
    public ListChatAiRunMetricUseCase listChatAiRunMetricUseCase(ChatAiRunMetricRepository repository) {
        return new ListChatAiRunMetricUseCase(repository);
    }

    @Bean
    public UpdateChatAiRunMetricUseCase updateChatAiRunMetricUseCase(ChatAiRunMetricRepository repository) {
        return new UpdateChatAiRunMetricUseCase(repository);
    }

    @Bean
    public DeleteChatAiRunMetricUseCase deleteChatAiRunMetricUseCase(ChatAiRunMetricRepository repository) {
        return new DeleteChatAiRunMetricUseCase(repository);
    }
}
