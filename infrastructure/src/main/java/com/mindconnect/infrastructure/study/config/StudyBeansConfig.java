package com.mindconnect.infrastructure.study.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mindconnect.application.study.usecase.DeleteStudyUseCase;
import com.mindconnect.application.study.usecase.GetStudyByIdUseCase;
import com.mindconnect.application.study.usecase.ListStudyUseCase;
import com.mindconnect.application.study.usecase.RegisterStudyUseCase;
import com.mindconnect.application.study.usecase.UpdateStudyUseCase;
import com.mindconnect.domain.study.port.repository.StudyRepository;
import com.mindconnect.infrastructure.study.adapters.out.persistence.mappers.StudyPersistenceMapper;
import com.mindconnect.infrastructure.study.adapters.out.persistence.repositories.StudyJpaRepository;
import com.mindconnect.infrastructure.study.adapters.out.persistence.repositories.StudyRepositoryAdapter;

/**
 * Conecta las piezas del contexto study: mapper, repositorio y casos de uso.
 * Los casos de uso no usan Spring; aquí se crean y se les entrega lo que necesitan.
 */
@Configuration
public class StudyBeansConfig {

    @Bean
    public StudyPersistenceMapper studyPersistenceMapper() {
        return new StudyPersistenceMapper();
    }

    @Bean
    public StudyRepository studyRepository(StudyJpaRepository repository, StudyPersistenceMapper mapper) {
        return new StudyRepositoryAdapter(repository, mapper);
    }

    @Bean
    public RegisterStudyUseCase registerStudyUseCase(StudyRepository repository) {
        return new RegisterStudyUseCase(repository);
    }

    @Bean
    public GetStudyByIdUseCase getStudyByIdUseCase(StudyRepository repository) {
        return new GetStudyByIdUseCase(repository);
    }

    @Bean
    public ListStudyUseCase listStudyUseCase(StudyRepository repository) {
        return new ListStudyUseCase(repository);
    }

    @Bean
    public UpdateStudyUseCase updateStudyUseCase(StudyRepository repository) {
        return new UpdateStudyUseCase(repository);
    }

    @Bean
    public DeleteStudyUseCase deleteStudyUseCase(StudyRepository repository) {
        return new DeleteStudyUseCase(repository);
    }
}
