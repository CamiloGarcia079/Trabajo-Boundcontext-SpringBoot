package com.mindconnect.infrastructure.riskassessment.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mindconnect.application.riskassessment.usecase.DeleteRiskAssessmentUseCase;
import com.mindconnect.application.riskassessment.usecase.GetRiskAssessmentByIdUseCase;
import com.mindconnect.application.riskassessment.usecase.ListRiskAssessmentUseCase;
import com.mindconnect.application.riskassessment.usecase.RegisterRiskAssessmentUseCase;
import com.mindconnect.application.riskassessment.usecase.UpdateRiskAssessmentUseCase;
import com.mindconnect.domain.riskassessment.port.repository.RiskAssessmentRepository;
import com.mindconnect.infrastructure.riskassessment.adapters.out.persistence.mappers.RiskAssessmentPersistenceMapper;
import com.mindconnect.infrastructure.riskassessment.adapters.out.persistence.repositories.RiskAssessmentJpaRepository;
import com.mindconnect.infrastructure.riskassessment.adapters.out.persistence.repositories.RiskAssessmentRepositoryAdapter;

/**
 * Conecta las piezas del contexto riskassessment: mapper, repositorio y casos de uso.
 * Los casos de uso no usan Spring; aquí se crean y se les entrega lo que necesitan.
 */
@Configuration
public class RiskAssessmentBeansConfig {

    @Bean
    public RiskAssessmentPersistenceMapper riskAssessmentPersistenceMapper() {
        return new RiskAssessmentPersistenceMapper();
    }

    @Bean
    public RiskAssessmentRepository riskAssessmentRepository(RiskAssessmentJpaRepository repository, RiskAssessmentPersistenceMapper mapper) {
        return new RiskAssessmentRepositoryAdapter(repository, mapper);
    }

    @Bean
    public RegisterRiskAssessmentUseCase registerRiskAssessmentUseCase(RiskAssessmentRepository repository) {
        return new RegisterRiskAssessmentUseCase(repository);
    }

    @Bean
    public GetRiskAssessmentByIdUseCase getRiskAssessmentByIdUseCase(RiskAssessmentRepository repository) {
        return new GetRiskAssessmentByIdUseCase(repository);
    }

    @Bean
    public ListRiskAssessmentUseCase listRiskAssessmentUseCase(RiskAssessmentRepository repository) {
        return new ListRiskAssessmentUseCase(repository);
    }

    @Bean
    public UpdateRiskAssessmentUseCase updateRiskAssessmentUseCase(RiskAssessmentRepository repository) {
        return new UpdateRiskAssessmentUseCase(repository);
    }

    @Bean
    public DeleteRiskAssessmentUseCase deleteRiskAssessmentUseCase(RiskAssessmentRepository repository) {
        return new DeleteRiskAssessmentUseCase(repository);
    }
}
