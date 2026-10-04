package com.mindconnect.infrastructure.escalationstatus.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mindconnect.application.escalationstatus.usecase.DeleteEscalationStatusUseCase;
import com.mindconnect.application.escalationstatus.usecase.GetEscalationStatusByIdUseCase;
import com.mindconnect.application.escalationstatus.usecase.ListEscalationStatusUseCase;
import com.mindconnect.application.escalationstatus.usecase.RegisterEscalationStatusUseCase;
import com.mindconnect.application.escalationstatus.usecase.UpdateEscalationStatusUseCase;
import com.mindconnect.domain.escalationstatus.port.repository.EscalationStatusRepository;
import com.mindconnect.infrastructure.escalationstatus.adapters.out.persistence.mappers.EscalationStatusPersistenceMapper;
import com.mindconnect.infrastructure.escalationstatus.adapters.out.persistence.repositories.EscalationStatusJpaRepository;
import com.mindconnect.infrastructure.escalationstatus.adapters.out.persistence.repositories.EscalationStatusRepositoryAdapter;

/**
 * Conecta las piezas del contexto escalationstatus: mapper, repositorio y casos de uso.
 * Los casos de uso no usan Spring; aquí se crean y se les entrega lo que necesitan.
 */
@Configuration
public class EscalationStatusBeansConfig {

    @Bean
    public EscalationStatusPersistenceMapper escalationStatusPersistenceMapper() {
        return new EscalationStatusPersistenceMapper();
    }

    @Bean
    public EscalationStatusRepository escalationStatusRepository(EscalationStatusJpaRepository repository, EscalationStatusPersistenceMapper mapper) {
        return new EscalationStatusRepositoryAdapter(repository, mapper);
    }

    @Bean
    public RegisterEscalationStatusUseCase registerEscalationStatusUseCase(EscalationStatusRepository repository) {
        return new RegisterEscalationStatusUseCase(repository);
    }

    @Bean
    public GetEscalationStatusByIdUseCase getEscalationStatusByIdUseCase(EscalationStatusRepository repository) {
        return new GetEscalationStatusByIdUseCase(repository);
    }

    @Bean
    public ListEscalationStatusUseCase listEscalationStatusUseCase(EscalationStatusRepository repository) {
        return new ListEscalationStatusUseCase(repository);
    }

    @Bean
    public UpdateEscalationStatusUseCase updateEscalationStatusUseCase(EscalationStatusRepository repository) {
        return new UpdateEscalationStatusUseCase(repository);
    }

    @Bean
    public DeleteEscalationStatusUseCase deleteEscalationStatusUseCase(EscalationStatusRepository repository) {
        return new DeleteEscalationStatusUseCase(repository);
    }
}
