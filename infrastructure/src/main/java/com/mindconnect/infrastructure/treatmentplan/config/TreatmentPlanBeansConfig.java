package com.mindconnect.infrastructure.treatmentplan.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mindconnect.application.treatmentplan.usecase.DeleteTreatmentPlanUseCase;
import com.mindconnect.application.treatmentplan.usecase.GetTreatmentPlanByIdUseCase;
import com.mindconnect.application.treatmentplan.usecase.ListTreatmentPlanUseCase;
import com.mindconnect.application.treatmentplan.usecase.RegisterTreatmentPlanUseCase;
import com.mindconnect.application.treatmentplan.usecase.UpdateTreatmentPlanUseCase;
import com.mindconnect.domain.treatmentplan.port.repository.TreatmentPlanRepository;
import com.mindconnect.infrastructure.treatmentplan.adapters.out.persistence.mappers.TreatmentPlanPersistenceMapper;
import com.mindconnect.infrastructure.treatmentplan.adapters.out.persistence.repositories.TreatmentPlanJpaRepository;
import com.mindconnect.infrastructure.treatmentplan.adapters.out.persistence.repositories.TreatmentPlanRepositoryAdapter;

/**
 * Conecta las piezas del contexto treatmentplan: mapper, repositorio y casos de uso.
 * Los casos de uso no usan Spring; aquí se crean y se les entrega lo que necesitan.
 */
@Configuration
public class TreatmentPlanBeansConfig {

    @Bean
    public TreatmentPlanPersistenceMapper treatmentPlanPersistenceMapper() {
        return new TreatmentPlanPersistenceMapper();
    }

    @Bean
    public TreatmentPlanRepository treatmentPlanRepository(TreatmentPlanJpaRepository repository, TreatmentPlanPersistenceMapper mapper) {
        return new TreatmentPlanRepositoryAdapter(repository, mapper);
    }

    @Bean
    public RegisterTreatmentPlanUseCase registerTreatmentPlanUseCase(TreatmentPlanRepository repository) {
        return new RegisterTreatmentPlanUseCase(repository);
    }

    @Bean
    public GetTreatmentPlanByIdUseCase getTreatmentPlanByIdUseCase(TreatmentPlanRepository repository) {
        return new GetTreatmentPlanByIdUseCase(repository);
    }

    @Bean
    public ListTreatmentPlanUseCase listTreatmentPlanUseCase(TreatmentPlanRepository repository) {
        return new ListTreatmentPlanUseCase(repository);
    }

    @Bean
    public UpdateTreatmentPlanUseCase updateTreatmentPlanUseCase(TreatmentPlanRepository repository) {
        return new UpdateTreatmentPlanUseCase(repository);
    }

    @Bean
    public DeleteTreatmentPlanUseCase deleteTreatmentPlanUseCase(TreatmentPlanRepository repository) {
        return new DeleteTreatmentPlanUseCase(repository);
    }
}
