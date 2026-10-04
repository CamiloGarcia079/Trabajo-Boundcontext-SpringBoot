package com.mindconnect.infrastructure.messagetype.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mindconnect.application.messagetype.usecase.DeleteMessageTypeUseCase;
import com.mindconnect.application.messagetype.usecase.GetMessageTypeByIdUseCase;
import com.mindconnect.application.messagetype.usecase.ListMessageTypeUseCase;
import com.mindconnect.application.messagetype.usecase.RegisterMessageTypeUseCase;
import com.mindconnect.application.messagetype.usecase.UpdateMessageTypeUseCase;
import com.mindconnect.domain.messagetype.port.repository.MessageTypeRepository;
import com.mindconnect.infrastructure.messagetype.adapters.out.persistence.mappers.MessageTypePersistenceMapper;
import com.mindconnect.infrastructure.messagetype.adapters.out.persistence.repositories.MessageTypeJpaRepository;
import com.mindconnect.infrastructure.messagetype.adapters.out.persistence.repositories.MessageTypeRepositoryAdapter;

/**
 * Conecta las piezas del contexto messagetype: mapper, repositorio y casos de uso.
 * Los casos de uso no usan Spring; aquí se crean y se les entrega lo que necesitan.
 */
@Configuration
public class MessageTypeBeansConfig {

    @Bean
    public MessageTypePersistenceMapper messageTypePersistenceMapper() {
        return new MessageTypePersistenceMapper();
    }

    @Bean
    public MessageTypeRepository messageTypeRepository(MessageTypeJpaRepository repository, MessageTypePersistenceMapper mapper) {
        return new MessageTypeRepositoryAdapter(repository, mapper);
    }

    @Bean
    public RegisterMessageTypeUseCase registerMessageTypeUseCase(MessageTypeRepository repository) {
        return new RegisterMessageTypeUseCase(repository);
    }

    @Bean
    public GetMessageTypeByIdUseCase getMessageTypeByIdUseCase(MessageTypeRepository repository) {
        return new GetMessageTypeByIdUseCase(repository);
    }

    @Bean
    public ListMessageTypeUseCase listMessageTypeUseCase(MessageTypeRepository repository) {
        return new ListMessageTypeUseCase(repository);
    }

    @Bean
    public UpdateMessageTypeUseCase updateMessageTypeUseCase(MessageTypeRepository repository) {
        return new UpdateMessageTypeUseCase(repository);
    }

    @Bean
    public DeleteMessageTypeUseCase deleteMessageTypeUseCase(MessageTypeRepository repository) {
        return new DeleteMessageTypeUseCase(repository);
    }
}
