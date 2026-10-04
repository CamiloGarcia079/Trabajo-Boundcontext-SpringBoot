package com.mindconnect.infrastructure.encountertype.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mindconnect.application.encountertype.usecase.DeleteEncounterTypeUseCase;
import com.mindconnect.application.encountertype.usecase.GetEncounterTypeByIdUseCase;
import com.mindconnect.application.encountertype.usecase.ListEncounterTypeUseCase;
import com.mindconnect.application.encountertype.usecase.RegisterEncounterTypeUseCase;
import com.mindconnect.application.encountertype.usecase.UpdateEncounterTypeUseCase;
import com.mindconnect.domain.encountertype.port.repository.EncounterTypeRepository;
import com.mindconnect.infrastructure.encountertype.adapters.out.persistence.mappers.EncounterTypePersistenceMapper;
import com.mindconnect.infrastructure.encountertype.adapters.out.persistence.repositories.EncounterTypeJpaRepository;
import com.mindconnect.infrastructure.encountertype.adapters.out.persistence.repositories.EncounterTypeRepositoryAdapter;

/**
 * Conecta las piezas del contexto encountertype: mapper, repositorio y casos de uso.
 * Los casos de uso no usan Spring; aquí se crean y se les entrega lo que necesitan.
 */
@Configuration
public class EncounterTypeBeansConfig {

    @Bean
    public EncounterTypePersistenceMapper encounterTypePersistenceMapper() {
        return new EncounterTypePersistenceMapper();
    }

    @Bean
    public EncounterTypeRepository encounterTypeRepository(EncounterTypeJpaRepository repository, EncounterTypePersistenceMapper mapper) {
        return new EncounterTypeRepositoryAdapter(repository, mapper);
    }

    @Bean
    public RegisterEncounterTypeUseCase registerEncounterTypeUseCase(EncounterTypeRepository repository) {
        return new RegisterEncounterTypeUseCase(repository);
    }

    @Bean
    public GetEncounterTypeByIdUseCase getEncounterTypeByIdUseCase(EncounterTypeRepository repository) {
        return new GetEncounterTypeByIdUseCase(repository);
    }

    @Bean
    public ListEncounterTypeUseCase listEncounterTypeUseCase(EncounterTypeRepository repository) {
        return new ListEncounterTypeUseCase(repository);
    }

    @Bean
    public UpdateEncounterTypeUseCase updateEncounterTypeUseCase(EncounterTypeRepository repository) {
        return new UpdateEncounterTypeUseCase(repository);
    }

    @Bean
    public DeleteEncounterTypeUseCase deleteEncounterTypeUseCase(EncounterTypeRepository repository) {
        return new DeleteEncounterTypeUseCase(repository);
    }
}
