package com.mindconnect.infrastructure.professionalstudy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mindconnect.application.professionalstudy.usecase.DeleteProfessionalStudyUseCase;
import com.mindconnect.application.professionalstudy.usecase.GetProfessionalStudyByIdUseCase;
import com.mindconnect.application.professionalstudy.usecase.ListProfessionalStudyUseCase;
import com.mindconnect.application.professionalstudy.usecase.RegisterProfessionalStudyUseCase;
import com.mindconnect.application.professionalstudy.usecase.UpdateProfessionalStudyUseCase;
import com.mindconnect.domain.professionalstudy.port.repository.ProfessionalStudyRepository;
import com.mindconnect.infrastructure.professionalstudy.adapters.out.persistence.mappers.ProfessionalStudyPersistenceMapper;
import com.mindconnect.infrastructure.professionalstudy.adapters.out.persistence.repositories.ProfessionalStudyJpaRepository;
import com.mindconnect.infrastructure.professionalstudy.adapters.out.persistence.repositories.ProfessionalStudyRepositoryAdapter;

/**
 * Conecta las piezas del contexto professionalstudy: mapper, repositorio y casos de uso.
 * Los casos de uso no usan Spring; aquí se crean y se les entrega lo que necesitan.
 */
@Configuration
public class ProfessionalStudyBeansConfig {

    @Bean
    public ProfessionalStudyPersistenceMapper professionalStudyPersistenceMapper() {
        return new ProfessionalStudyPersistenceMapper();
    }

    @Bean
    public ProfessionalStudyRepository professionalStudyRepository(ProfessionalStudyJpaRepository repository, ProfessionalStudyPersistenceMapper mapper) {
        return new ProfessionalStudyRepositoryAdapter(repository, mapper);
    }

    @Bean
    public RegisterProfessionalStudyUseCase registerProfessionalStudyUseCase(ProfessionalStudyRepository repository) {
        return new RegisterProfessionalStudyUseCase(repository);
    }

    @Bean
    public GetProfessionalStudyByIdUseCase getProfessionalStudyByIdUseCase(ProfessionalStudyRepository repository) {
        return new GetProfessionalStudyByIdUseCase(repository);
    }

    @Bean
    public ListProfessionalStudyUseCase listProfessionalStudyUseCase(ProfessionalStudyRepository repository) {
        return new ListProfessionalStudyUseCase(repository);
    }

    @Bean
    public UpdateProfessionalStudyUseCase updateProfessionalStudyUseCase(ProfessionalStudyRepository repository) {
        return new UpdateProfessionalStudyUseCase(repository);
    }

    @Bean
    public DeleteProfessionalStudyUseCase deleteProfessionalStudyUseCase(ProfessionalStudyRepository repository) {
        return new DeleteProfessionalStudyUseCase(repository);
    }
}
