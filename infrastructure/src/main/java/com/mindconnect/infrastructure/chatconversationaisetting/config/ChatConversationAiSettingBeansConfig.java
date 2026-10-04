package com.mindconnect.infrastructure.chatconversationaisetting.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mindconnect.application.chatconversationaisetting.usecase.DeleteChatConversationAiSettingUseCase;
import com.mindconnect.application.chatconversationaisetting.usecase.GetChatConversationAiSettingByIdUseCase;
import com.mindconnect.application.chatconversationaisetting.usecase.ListChatConversationAiSettingUseCase;
import com.mindconnect.application.chatconversationaisetting.usecase.RegisterChatConversationAiSettingUseCase;
import com.mindconnect.application.chatconversationaisetting.usecase.UpdateChatConversationAiSettingUseCase;
import com.mindconnect.domain.chatconversationaisetting.port.repository.ChatConversationAiSettingRepository;
import com.mindconnect.infrastructure.chatconversationaisetting.adapters.out.persistence.mappers.ChatConversationAiSettingPersistenceMapper;
import com.mindconnect.infrastructure.chatconversationaisetting.adapters.out.persistence.repositories.ChatConversationAiSettingJpaRepository;
import com.mindconnect.infrastructure.chatconversationaisetting.adapters.out.persistence.repositories.ChatConversationAiSettingRepositoryAdapter;

/**
 * Conecta las piezas del contexto chatconversationaisetting: mapper, repositorio y casos de uso.
 * Los casos de uso no usan Spring; aquí se crean y se les entrega lo que necesitan.
 */
@Configuration
public class ChatConversationAiSettingBeansConfig {

    @Bean
    public ChatConversationAiSettingPersistenceMapper chatConversationAiSettingPersistenceMapper() {
        return new ChatConversationAiSettingPersistenceMapper();
    }

    @Bean
    public ChatConversationAiSettingRepository chatConversationAiSettingRepository(ChatConversationAiSettingJpaRepository repository, ChatConversationAiSettingPersistenceMapper mapper) {
        return new ChatConversationAiSettingRepositoryAdapter(repository, mapper);
    }

    @Bean
    public RegisterChatConversationAiSettingUseCase registerChatConversationAiSettingUseCase(ChatConversationAiSettingRepository repository) {
        return new RegisterChatConversationAiSettingUseCase(repository);
    }

    @Bean
    public GetChatConversationAiSettingByIdUseCase getChatConversationAiSettingByIdUseCase(ChatConversationAiSettingRepository repository) {
        return new GetChatConversationAiSettingByIdUseCase(repository);
    }

    @Bean
    public ListChatConversationAiSettingUseCase listChatConversationAiSettingUseCase(ChatConversationAiSettingRepository repository) {
        return new ListChatConversationAiSettingUseCase(repository);
    }

    @Bean
    public UpdateChatConversationAiSettingUseCase updateChatConversationAiSettingUseCase(ChatConversationAiSettingRepository repository) {
        return new UpdateChatConversationAiSettingUseCase(repository);
    }

    @Bean
    public DeleteChatConversationAiSettingUseCase deleteChatConversationAiSettingUseCase(ChatConversationAiSettingRepository repository) {
        return new DeleteChatConversationAiSettingUseCase(repository);
    }
}
