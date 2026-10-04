package com.mindconnect.infrastructure.treatmentgoalstatus.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mindconnect.application.treatmentgoalstatus.usecase.DeleteTreatmentGoalStatusUseCase;
import com.mindconnect.application.treatmentgoalstatus.usecase.GetTreatmentGoalStatusByIdUseCase;
import com.mindconnect.application.treatmentgoalstatus.usecase.ListTreatmentGoalStatusUseCase;
import com.mindconnect.application.treatmentgoalstatus.usecase.RegisterTreatmentGoalStatusUseCase;
import com.mindconnect.application.treatmentgoalstatus.usecase.UpdateTreatmentGoalStatusUseCase;
import com.mindconnect.domain.treatmentgoalstatus.port.repository.TreatmentGoalStatusRepository;
import com.mindconnect.infrastructure.treatmentgoalstatus.adapters.out.persistence.mappers.TreatmentGoalStatusPersistenceMapper;
import com.mindconnect.infrastructure.treatmentgoalstatus.adapters.out.persistence.repositories.TreatmentGoalStatusJpaRepository;
import com.mindconnect.infrastructure.treatmentgoalstatus.adapters.out.persistence.repositories.TreatmentGoalStatusRepositoryAdapter;

/**
 * Conecta las piezas del contexto treatmentgoalstatus: mapper, repositorio y casos de uso.
 * Los casos de uso no usan Spring; aquí se crean y se les entrega lo que necesitan.
 */
@Configuration
public class TreatmentGoalStatusBeansConfig {

    @Bean
    public TreatmentGoalStatusPersistenceMapper treatmentGoalStatusPersistenceMapper() {
        return new TreatmentGoalStatusPersistenceMapper();
    }

    @Bean
    public TreatmentGoalStatusRepository treatmentGoalStatusRepository(TreatmentGoalStatusJpaRepository repository, TreatmentGoalStatusPersistenceMapper mapper) {
        return new TreatmentGoalStatusRepositoryAdapter(repository, mapper);
    }

    @Bean
    public RegisterTreatmentGoalStatusUseCase registerTreatmentGoalStatusUseCase(TreatmentGoalStatusRepository repository) {
        return new RegisterTreatmentGoalStatusUseCase(repository);
    }

    @Bean
    public GetTreatmentGoalStatusByIdUseCase getTreatmentGoalStatusByIdUseCase(TreatmentGoalStatusRepository repository) {
        return new GetTreatmentGoalStatusByIdUseCase(repository);
    }

    @Bean
    public ListTreatmentGoalStatusUseCase listTreatmentGoalStatusUseCase(TreatmentGoalStatusRepository repository) {
        return new ListTreatmentGoalStatusUseCase(repository);
    }

    @Bean
    public UpdateTreatmentGoalStatusUseCase updateTreatmentGoalStatusUseCase(TreatmentGoalStatusRepository repository) {
        return new UpdateTreatmentGoalStatusUseCase(repository);
    }

    @Bean
    public DeleteTreatmentGoalStatusUseCase deleteTreatmentGoalStatusUseCase(TreatmentGoalStatusRepository repository) {
        return new DeleteTreatmentGoalStatusUseCase(repository);
    }
}
