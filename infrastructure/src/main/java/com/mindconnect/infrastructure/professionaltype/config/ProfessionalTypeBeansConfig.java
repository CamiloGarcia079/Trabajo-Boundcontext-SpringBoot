package com.mindconnect.infrastructure.professionaltype.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mindconnect.application.professionaltype.usecase.DeleteProfessionalTypeUseCase;
import com.mindconnect.application.professionaltype.usecase.GetProfessionalTypeByIdUseCase;
import com.mindconnect.application.professionaltype.usecase.ListProfessionalTypeUseCase;
import com.mindconnect.application.professionaltype.usecase.RegisterProfessionalTypeUseCase;
import com.mindconnect.application.professionaltype.usecase.UpdateProfessionalTypeUseCase;
import com.mindconnect.domain.professionaltype.port.repository.ProfessionalTypeRepository;
import com.mindconnect.infrastructure.professionaltype.adapters.out.persistence.mappers.ProfessionalTypePersistenceMapper;
import com.mindconnect.infrastructure.professionaltype.adapters.out.persistence.repositories.ProfessionalTypeJpaRepository;
import com.mindconnect.infrastructure.professionaltype.adapters.out.persistence.repositories.ProfessionalTypeRepositoryAdapter;

/**
 * Conecta las piezas del contexto professionaltype: mapper, repositorio y casos de uso.
 * Los casos de uso no usan Spring; aquí se crean y se les entrega lo que necesitan.
 */
@Configuration
public class ProfessionalTypeBeansConfig {

    @Bean
    public ProfessionalTypePersistenceMapper professionalTypePersistenceMapper() {
        return new ProfessionalTypePersistenceMapper();
    }

    @Bean
    public ProfessionalTypeRepository professionalTypeRepository(ProfessionalTypeJpaRepository repository, ProfessionalTypePersistenceMapper mapper) {
        return new ProfessionalTypeRepositoryAdapter(repository, mapper);
    }

    @Bean
    public RegisterProfessionalTypeUseCase registerProfessionalTypeUseCase(ProfessionalTypeRepository repository) {
        return new RegisterProfessionalTypeUseCase(repository);
    }

    @Bean
    public GetProfessionalTypeByIdUseCase getProfessionalTypeByIdUseCase(ProfessionalTypeRepository repository) {
        return new GetProfessionalTypeByIdUseCase(repository);
    }

    @Bean
    public ListProfessionalTypeUseCase listProfessionalTypeUseCase(ProfessionalTypeRepository repository) {
        return new ListProfessionalTypeUseCase(repository);
    }

    @Bean
    public UpdateProfessionalTypeUseCase updateProfessionalTypeUseCase(ProfessionalTypeRepository repository) {
        return new UpdateProfessionalTypeUseCase(repository);
    }

    @Bean
    public DeleteProfessionalTypeUseCase deleteProfessionalTypeUseCase(ProfessionalTypeRepository repository) {
        return new DeleteProfessionalTypeUseCase(repository);
    }
}
