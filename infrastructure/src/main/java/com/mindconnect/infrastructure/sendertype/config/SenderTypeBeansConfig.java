package com.mindconnect.infrastructure.sendertype.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mindconnect.application.sendertype.usecase.DeleteSenderTypeUseCase;
import com.mindconnect.application.sendertype.usecase.GetSenderTypeByIdUseCase;
import com.mindconnect.application.sendertype.usecase.ListSenderTypeUseCase;
import com.mindconnect.application.sendertype.usecase.RegisterSenderTypeUseCase;
import com.mindconnect.application.sendertype.usecase.UpdateSenderTypeUseCase;
import com.mindconnect.domain.sendertype.port.repository.SenderTypeRepository;
import com.mindconnect.infrastructure.sendertype.adapters.out.persistence.mappers.SenderTypePersistenceMapper;
import com.mindconnect.infrastructure.sendertype.adapters.out.persistence.repositories.SenderTypeJpaRepository;
import com.mindconnect.infrastructure.sendertype.adapters.out.persistence.repositories.SenderTypeRepositoryAdapter;

/**
 * Conecta las piezas del contexto sendertype: mapper, repositorio y casos de uso.
 * Los casos de uso no usan Spring; aquí se crean y se les entrega lo que necesitan.
 */
@Configuration
public class SenderTypeBeansConfig {

    @Bean
    public SenderTypePersistenceMapper senderTypePersistenceMapper() {
        return new SenderTypePersistenceMapper();
    }

    @Bean
    public SenderTypeRepository senderTypeRepository(SenderTypeJpaRepository repository, SenderTypePersistenceMapper mapper) {
        return new SenderTypeRepositoryAdapter(repository, mapper);
    }

    @Bean
    public RegisterSenderTypeUseCase registerSenderTypeUseCase(SenderTypeRepository repository) {
        return new RegisterSenderTypeUseCase(repository);
    }

    @Bean
    public GetSenderTypeByIdUseCase getSenderTypeByIdUseCase(SenderTypeRepository repository) {
        return new GetSenderTypeByIdUseCase(repository);
    }

    @Bean
    public ListSenderTypeUseCase listSenderTypeUseCase(SenderTypeRepository repository) {
        return new ListSenderTypeUseCase(repository);
    }

    @Bean
    public UpdateSenderTypeUseCase updateSenderTypeUseCase(SenderTypeRepository repository) {
        return new UpdateSenderTypeUseCase(repository);
    }

    @Bean
    public DeleteSenderTypeUseCase deleteSenderTypeUseCase(SenderTypeRepository repository) {
        return new DeleteSenderTypeUseCase(repository);
    }
}
