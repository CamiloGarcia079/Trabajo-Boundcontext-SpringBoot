package com.mindconnect.infrastructure.encounterstatus.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mindconnect.application.encounterstatus.usecase.DeleteEncounterStatusUseCase;
import com.mindconnect.application.encounterstatus.usecase.GetEncounterStatusByIdUseCase;
import com.mindconnect.application.encounterstatus.usecase.ListEncounterStatusUseCase;
import com.mindconnect.application.encounterstatus.usecase.RegisterEncounterStatusUseCase;
import com.mindconnect.application.encounterstatus.usecase.UpdateEncounterStatusUseCase;
import com.mindconnect.domain.encounterstatus.port.repository.EncounterStatusRepository;
import com.mindconnect.infrastructure.encounterstatus.adapters.out.persistence.mappers.EncounterStatusPersistenceMapper;
import com.mindconnect.infrastructure.encounterstatus.adapters.out.persistence.repositories.EncounterStatusJpaRepository;
import com.mindconnect.infrastructure.encounterstatus.adapters.out.persistence.repositories.EncounterStatusRepositoryAdapter;

/**
 * Conecta las piezas del contexto encounterstatus: mapper, repositorio y casos de uso.
 * Los casos de uso no usan Spring; aquí se crean y se les entrega lo que necesitan.
 */
@Configuration
public class EncounterStatusBeansConfig {

    @Bean
    public EncounterStatusPersistenceMapper encounterStatusPersistenceMapper() {
        return new EncounterStatusPersistenceMapper();
    }

    @Bean
    public EncounterStatusRepository encounterStatusRepository(EncounterStatusJpaRepository repository, EncounterStatusPersistenceMapper mapper) {
        return new EncounterStatusRepositoryAdapter(repository, mapper);
    }

    @Bean
    public RegisterEncounterStatusUseCase registerEncounterStatusUseCase(EncounterStatusRepository repository) {
        return new RegisterEncounterStatusUseCase(repository);
    }

    @Bean
    public GetEncounterStatusByIdUseCase getEncounterStatusByIdUseCase(EncounterStatusRepository repository) {
        return new GetEncounterStatusByIdUseCase(repository);
    }

    @Bean
    public ListEncounterStatusUseCase listEncounterStatusUseCase(EncounterStatusRepository repository) {
        return new ListEncounterStatusUseCase(repository);
    }

    @Bean
    public UpdateEncounterStatusUseCase updateEncounterStatusUseCase(EncounterStatusRepository repository) {
        return new UpdateEncounterStatusUseCase(repository);
    }

    @Bean
    public DeleteEncounterStatusUseCase deleteEncounterStatusUseCase(EncounterStatusRepository repository) {
        return new DeleteEncounterStatusUseCase(repository);
    }
}
