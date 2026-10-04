package com.mindconnect.infrastructure.risklevel.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mindconnect.application.risklevel.usecase.DeleteRiskLevelUseCase;
import com.mindconnect.application.risklevel.usecase.GetRiskLevelByIdUseCase;
import com.mindconnect.application.risklevel.usecase.ListRiskLevelUseCase;
import com.mindconnect.application.risklevel.usecase.RegisterRiskLevelUseCase;
import com.mindconnect.application.risklevel.usecase.UpdateRiskLevelUseCase;
import com.mindconnect.domain.risklevel.port.repository.RiskLevelRepository;
import com.mindconnect.infrastructure.risklevel.adapters.out.persistence.mappers.RiskLevelPersistenceMapper;
import com.mindconnect.infrastructure.risklevel.adapters.out.persistence.repositories.RiskLevelJpaRepository;
import com.mindconnect.infrastructure.risklevel.adapters.out.persistence.repositories.RiskLevelRepositoryAdapter;

/**
 * Conecta las piezas del contexto risklevel: mapper, repositorio y casos de uso.
 * Los casos de uso no usan Spring; aquí se crean y se les entrega lo que necesitan.
 */
@Configuration
public class RiskLevelBeansConfig {

    @Bean
    public RiskLevelPersistenceMapper riskLevelPersistenceMapper() {
        return new RiskLevelPersistenceMapper();
    }

    @Bean
    public RiskLevelRepository riskLevelRepository(RiskLevelJpaRepository repository, RiskLevelPersistenceMapper mapper) {
        return new RiskLevelRepositoryAdapter(repository, mapper);
    }

    @Bean
    public RegisterRiskLevelUseCase registerRiskLevelUseCase(RiskLevelRepository repository) {
        return new RegisterRiskLevelUseCase(repository);
    }

    @Bean
    public GetRiskLevelByIdUseCase getRiskLevelByIdUseCase(RiskLevelRepository repository) {
        return new GetRiskLevelByIdUseCase(repository);
    }

    @Bean
    public ListRiskLevelUseCase listRiskLevelUseCase(RiskLevelRepository repository) {
        return new ListRiskLevelUseCase(repository);
    }

    @Bean
    public UpdateRiskLevelUseCase updateRiskLevelUseCase(RiskLevelRepository repository) {
        return new UpdateRiskLevelUseCase(repository);
    }

    @Bean
    public DeleteRiskLevelUseCase deleteRiskLevelUseCase(RiskLevelRepository repository) {
        return new DeleteRiskLevelUseCase(repository);
    }
}
