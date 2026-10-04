package com.mindconnect.infrastructure.clinicalnote.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mindconnect.application.clinicalnote.usecase.DeleteClinicalNoteUseCase;
import com.mindconnect.application.clinicalnote.usecase.GetClinicalNoteByIdUseCase;
import com.mindconnect.application.clinicalnote.usecase.ListClinicalNoteUseCase;
import com.mindconnect.application.clinicalnote.usecase.RegisterClinicalNoteUseCase;
import com.mindconnect.application.clinicalnote.usecase.UpdateClinicalNoteUseCase;
import com.mindconnect.domain.clinicalnote.port.repository.ClinicalNoteRepository;
import com.mindconnect.infrastructure.clinicalnote.adapters.out.persistence.mappers.ClinicalNotePersistenceMapper;
import com.mindconnect.infrastructure.clinicalnote.adapters.out.persistence.repositories.ClinicalNoteJpaRepository;
import com.mindconnect.infrastructure.clinicalnote.adapters.out.persistence.repositories.ClinicalNoteRepositoryAdapter;

/**
 * Conecta las piezas del contexto clinicalnote: mapper, repositorio y casos de uso.
 * Los casos de uso no usan Spring; aquí se crean y se les entrega lo que necesitan.
 */
@Configuration
public class ClinicalNoteBeansConfig {

    @Bean
    public ClinicalNotePersistenceMapper clinicalNotePersistenceMapper() {
        return new ClinicalNotePersistenceMapper();
    }

    @Bean
    public ClinicalNoteRepository clinicalNoteRepository(ClinicalNoteJpaRepository repository, ClinicalNotePersistenceMapper mapper) {
        return new ClinicalNoteRepositoryAdapter(repository, mapper);
    }

    @Bean
    public RegisterClinicalNoteUseCase registerClinicalNoteUseCase(ClinicalNoteRepository repository) {
        return new RegisterClinicalNoteUseCase(repository);
    }

    @Bean
    public GetClinicalNoteByIdUseCase getClinicalNoteByIdUseCase(ClinicalNoteRepository repository) {
        return new GetClinicalNoteByIdUseCase(repository);
    }

    @Bean
    public ListClinicalNoteUseCase listClinicalNoteUseCase(ClinicalNoteRepository repository) {
        return new ListClinicalNoteUseCase(repository);
    }

    @Bean
    public UpdateClinicalNoteUseCase updateClinicalNoteUseCase(ClinicalNoteRepository repository) {
        return new UpdateClinicalNoteUseCase(repository);
    }

    @Bean
    public DeleteClinicalNoteUseCase deleteClinicalNoteUseCase(ClinicalNoteRepository repository) {
        return new DeleteClinicalNoteUseCase(repository);
    }
}
