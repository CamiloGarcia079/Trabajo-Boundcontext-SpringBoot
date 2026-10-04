package com.mindconnect.infrastructure.assessmenttype.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mindconnect.application.assessmenttype.usecase.DeleteAssessmentTypeUseCase;
import com.mindconnect.application.assessmenttype.usecase.GetAssessmentTypeByIdUseCase;
import com.mindconnect.application.assessmenttype.usecase.ListAssessmentTypeUseCase;
import com.mindconnect.application.assessmenttype.usecase.RegisterAssessmentTypeUseCase;
import com.mindconnect.application.assessmenttype.usecase.UpdateAssessmentTypeUseCase;
import com.mindconnect.domain.assessmenttype.port.repository.AssessmentTypeRepository;
import com.mindconnect.infrastructure.assessmenttype.adapters.out.persistence.mappers.AssessmentTypePersistenceMapper;
import com.mindconnect.infrastructure.assessmenttype.adapters.out.persistence.repositories.AssessmentTypeJpaRepository;
import com.mindconnect.infrastructure.assessmenttype.adapters.out.persistence.repositories.AssessmentTypeRepositoryAdapter;

/**
 * Conecta las piezas del contexto assessmenttype: mapper, repositorio y casos de uso.
 * Los casos de uso no usan Spring; aquí se crean y se les entrega lo que necesitan.
 */
@Configuration
public class AssessmentTypeBeansConfig {

    @Bean
    public AssessmentTypePersistenceMapper assessmentTypePersistenceMapper() {
        return new AssessmentTypePersistenceMapper();
    }

    @Bean
    public AssessmentTypeRepository assessmentTypeRepository(AssessmentTypeJpaRepository repository, AssessmentTypePersistenceMapper mapper) {
        return new AssessmentTypeRepositoryAdapter(repository, mapper);
    }

    @Bean
    public RegisterAssessmentTypeUseCase registerAssessmentTypeUseCase(AssessmentTypeRepository repository) {
        return new RegisterAssessmentTypeUseCase(repository);
    }

    @Bean
    public GetAssessmentTypeByIdUseCase getAssessmentTypeByIdUseCase(AssessmentTypeRepository repository) {
        return new GetAssessmentTypeByIdUseCase(repository);
    }

    @Bean
    public ListAssessmentTypeUseCase listAssessmentTypeUseCase(AssessmentTypeRepository repository) {
        return new ListAssessmentTypeUseCase(repository);
    }

    @Bean
    public UpdateAssessmentTypeUseCase updateAssessmentTypeUseCase(AssessmentTypeRepository repository) {
        return new UpdateAssessmentTypeUseCase(repository);
    }

    @Bean
    public DeleteAssessmentTypeUseCase deleteAssessmentTypeUseCase(AssessmentTypeRepository repository) {
        return new DeleteAssessmentTypeUseCase(repository);
    }
}
