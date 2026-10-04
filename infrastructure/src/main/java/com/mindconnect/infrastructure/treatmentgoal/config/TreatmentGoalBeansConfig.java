package com.mindconnect.infrastructure.treatmentgoal.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mindconnect.application.treatmentgoal.usecase.DeleteTreatmentGoalUseCase;
import com.mindconnect.application.treatmentgoal.usecase.GetTreatmentGoalByIdUseCase;
import com.mindconnect.application.treatmentgoal.usecase.ListTreatmentGoalUseCase;
import com.mindconnect.application.treatmentgoal.usecase.RegisterTreatmentGoalUseCase;
import com.mindconnect.application.treatmentgoal.usecase.UpdateTreatmentGoalUseCase;
import com.mindconnect.domain.treatmentgoal.port.repository.TreatmentGoalRepository;
import com.mindconnect.infrastructure.treatmentgoal.adapters.out.persistence.mappers.TreatmentGoalPersistenceMapper;
import com.mindconnect.infrastructure.treatmentgoal.adapters.out.persistence.repositories.TreatmentGoalJpaRepository;
import com.mindconnect.infrastructure.treatmentgoal.adapters.out.persistence.repositories.TreatmentGoalRepositoryAdapter;

/**
 * Conecta las piezas del contexto treatmentgoal: mapper, repositorio y casos de uso.
 * Los casos de uso no usan Spring; aquí se crean y se les entrega lo que necesitan.
 */
@Configuration
public class TreatmentGoalBeansConfig {

    @Bean
    public TreatmentGoalPersistenceMapper treatmentGoalPersistenceMapper() {
        return new TreatmentGoalPersistenceMapper();
    }

    @Bean
    public TreatmentGoalRepository treatmentGoalRepository(TreatmentGoalJpaRepository repository, TreatmentGoalPersistenceMapper mapper) {
        return new TreatmentGoalRepositoryAdapter(repository, mapper);
    }

    @Bean
    public RegisterTreatmentGoalUseCase registerTreatmentGoalUseCase(TreatmentGoalRepository repository) {
        return new RegisterTreatmentGoalUseCase(repository);
    }

    @Bean
    public GetTreatmentGoalByIdUseCase getTreatmentGoalByIdUseCase(TreatmentGoalRepository repository) {
        return new GetTreatmentGoalByIdUseCase(repository);
    }

    @Bean
    public ListTreatmentGoalUseCase listTreatmentGoalUseCase(TreatmentGoalRepository repository) {
        return new ListTreatmentGoalUseCase(repository);
    }

    @Bean
    public UpdateTreatmentGoalUseCase updateTreatmentGoalUseCase(TreatmentGoalRepository repository) {
        return new UpdateTreatmentGoalUseCase(repository);
    }

    @Bean
    public DeleteTreatmentGoalUseCase deleteTreatmentGoalUseCase(TreatmentGoalRepository repository) {
        return new DeleteTreatmentGoalUseCase(repository);
    }
}
