package com.mindconnect.infrastructure.treatmentstatus.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mindconnect.application.treatmentstatus.usecase.DeleteTreatmentStatusUseCase;
import com.mindconnect.application.treatmentstatus.usecase.GetTreatmentStatusByIdUseCase;
import com.mindconnect.application.treatmentstatus.usecase.ListTreatmentStatusUseCase;
import com.mindconnect.application.treatmentstatus.usecase.RegisterTreatmentStatusUseCase;
import com.mindconnect.application.treatmentstatus.usecase.UpdateTreatmentStatusUseCase;
import com.mindconnect.domain.treatmentstatus.port.repository.TreatmentStatusRepository;
import com.mindconnect.infrastructure.treatmentstatus.adapters.out.persistence.mappers.TreatmentStatusPersistenceMapper;
import com.mindconnect.infrastructure.treatmentstatus.adapters.out.persistence.repositories.TreatmentStatusJpaRepository;
import com.mindconnect.infrastructure.treatmentstatus.adapters.out.persistence.repositories.TreatmentStatusRepositoryAdapter;

/**
 * Conecta las piezas del contexto treatmentstatus: mapper, repositorio y casos de uso.
 * Los casos de uso no usan Spring; aquí se crean y se les entrega lo que necesitan.
 */
@Configuration
public class TreatmentStatusBeansConfig {

    @Bean
    public TreatmentStatusPersistenceMapper treatmentStatusPersistenceMapper() {
        return new TreatmentStatusPersistenceMapper();
    }

    @Bean
    public TreatmentStatusRepository treatmentStatusRepository(TreatmentStatusJpaRepository repository, TreatmentStatusPersistenceMapper mapper) {
        return new TreatmentStatusRepositoryAdapter(repository, mapper);
    }

    @Bean
    public RegisterTreatmentStatusUseCase registerTreatmentStatusUseCase(TreatmentStatusRepository repository) {
        return new RegisterTreatmentStatusUseCase(repository);
    }

    @Bean
    public GetTreatmentStatusByIdUseCase getTreatmentStatusByIdUseCase(TreatmentStatusRepository repository) {
        return new GetTreatmentStatusByIdUseCase(repository);
    }

    @Bean
    public ListTreatmentStatusUseCase listTreatmentStatusUseCase(TreatmentStatusRepository repository) {
        return new ListTreatmentStatusUseCase(repository);
    }

    @Bean
    public UpdateTreatmentStatusUseCase updateTreatmentStatusUseCase(TreatmentStatusRepository repository) {
        return new UpdateTreatmentStatusUseCase(repository);
    }

    @Bean
    public DeleteTreatmentStatusUseCase deleteTreatmentStatusUseCase(TreatmentStatusRepository repository) {
        return new DeleteTreatmentStatusUseCase(repository);
    }
}
