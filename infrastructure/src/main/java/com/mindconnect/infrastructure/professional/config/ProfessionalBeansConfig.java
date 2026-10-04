package com.mindconnect.infrastructure.professional.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mindconnect.application.professional.usecase.DeleteProfessionalUseCase;
import com.mindconnect.application.professional.usecase.GetProfessionalByIdUseCase;
import com.mindconnect.application.professional.usecase.ListProfessionalUseCase;
import com.mindconnect.application.professional.usecase.RegisterProfessionalUseCase;
import com.mindconnect.application.professional.usecase.UpdateProfessionalUseCase;
import com.mindconnect.domain.professional.port.repository.ProfessionalRepository;
import com.mindconnect.infrastructure.professional.adapters.out.persistence.mappers.ProfessionalPersistenceMapper;
import com.mindconnect.infrastructure.professional.adapters.out.persistence.repositories.ProfessionalJpaRepository;
import com.mindconnect.infrastructure.professional.adapters.out.persistence.repositories.ProfessionalRepositoryAdapter;

/**
 * Conecta las piezas del contexto professional: mapper, repositorio y casos de uso.
 * Los casos de uso no usan Spring; aquí se crean y se les entrega lo que necesitan.
 */
@Configuration
public class ProfessionalBeansConfig {

    @Bean
    public ProfessionalPersistenceMapper professionalPersistenceMapper() {
        return new ProfessionalPersistenceMapper();
    }

    @Bean
    public ProfessionalRepository professionalRepository(ProfessionalJpaRepository repository, ProfessionalPersistenceMapper mapper) {
        return new ProfessionalRepositoryAdapter(repository, mapper);
    }

    @Bean
    public RegisterProfessionalUseCase registerProfessionalUseCase(ProfessionalRepository repository) {
        return new RegisterProfessionalUseCase(repository);
    }

    @Bean
    public GetProfessionalByIdUseCase getProfessionalByIdUseCase(ProfessionalRepository repository) {
        return new GetProfessionalByIdUseCase(repository);
    }

    @Bean
    public ListProfessionalUseCase listProfessionalUseCase(ProfessionalRepository repository) {
        return new ListProfessionalUseCase(repository);
    }

    @Bean
    public UpdateProfessionalUseCase updateProfessionalUseCase(ProfessionalRepository repository) {
        return new UpdateProfessionalUseCase(repository);
    }

    @Bean
    public DeleteProfessionalUseCase deleteProfessionalUseCase(ProfessionalRepository repository) {
        return new DeleteProfessionalUseCase(repository);
    }
}
