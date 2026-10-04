package com.mindconnect.infrastructure.mentalstatusexam.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mindconnect.application.mentalstatusexam.usecase.DeleteMentalStatusExamUseCase;
import com.mindconnect.application.mentalstatusexam.usecase.GetMentalStatusExamByIdUseCase;
import com.mindconnect.application.mentalstatusexam.usecase.ListMentalStatusExamUseCase;
import com.mindconnect.application.mentalstatusexam.usecase.RegisterMentalStatusExamUseCase;
import com.mindconnect.application.mentalstatusexam.usecase.UpdateMentalStatusExamUseCase;
import com.mindconnect.domain.mentalstatusexam.port.repository.MentalStatusExamRepository;
import com.mindconnect.infrastructure.mentalstatusexam.adapters.out.persistence.mappers.MentalStatusExamPersistenceMapper;
import com.mindconnect.infrastructure.mentalstatusexam.adapters.out.persistence.repositories.MentalStatusExamJpaRepository;
import com.mindconnect.infrastructure.mentalstatusexam.adapters.out.persistence.repositories.MentalStatusExamRepositoryAdapter;

/**
 * Conecta las piezas del contexto mentalstatusexam: mapper, repositorio y casos de uso.
 * Los casos de uso no usan Spring; aquí se crean y se les entrega lo que necesitan.
 */
@Configuration
public class MentalStatusExamBeansConfig {

    @Bean
    public MentalStatusExamPersistenceMapper mentalStatusExamPersistenceMapper() {
        return new MentalStatusExamPersistenceMapper();
    }

    @Bean
    public MentalStatusExamRepository mentalStatusExamRepository(MentalStatusExamJpaRepository repository, MentalStatusExamPersistenceMapper mapper) {
        return new MentalStatusExamRepositoryAdapter(repository, mapper);
    }

    @Bean
    public RegisterMentalStatusExamUseCase registerMentalStatusExamUseCase(MentalStatusExamRepository repository) {
        return new RegisterMentalStatusExamUseCase(repository);
    }

    @Bean
    public GetMentalStatusExamByIdUseCase getMentalStatusExamByIdUseCase(MentalStatusExamRepository repository) {
        return new GetMentalStatusExamByIdUseCase(repository);
    }

    @Bean
    public ListMentalStatusExamUseCase listMentalStatusExamUseCase(MentalStatusExamRepository repository) {
        return new ListMentalStatusExamUseCase(repository);
    }

    @Bean
    public UpdateMentalStatusExamUseCase updateMentalStatusExamUseCase(MentalStatusExamRepository repository) {
        return new UpdateMentalStatusExamUseCase(repository);
    }

    @Bean
    public DeleteMentalStatusExamUseCase deleteMentalStatusExamUseCase(MentalStatusExamRepository repository) {
        return new DeleteMentalStatusExamUseCase(repository);
    }
}
