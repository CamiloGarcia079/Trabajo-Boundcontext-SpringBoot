package com.mindconnect.infrastructure.clinicalrecord.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mindconnect.application.clinicalrecord.usecase.DeleteClinicalRecordUseCase;
import com.mindconnect.application.clinicalrecord.usecase.GetClinicalRecordByIdUseCase;
import com.mindconnect.application.clinicalrecord.usecase.ListClinicalRecordUseCase;
import com.mindconnect.application.clinicalrecord.usecase.RegisterClinicalRecordUseCase;
import com.mindconnect.application.clinicalrecord.usecase.UpdateClinicalRecordUseCase;
import com.mindconnect.domain.clinicalrecord.port.repository.ClinicalRecordRepository;
import com.mindconnect.infrastructure.clinicalrecord.adapters.out.persistence.mappers.ClinicalRecordPersistenceMapper;
import com.mindconnect.infrastructure.clinicalrecord.adapters.out.persistence.repositories.ClinicalRecordJpaRepository;
import com.mindconnect.infrastructure.clinicalrecord.adapters.out.persistence.repositories.ClinicalRecordRepositoryAdapter;

/**
 * Conecta las piezas del contexto clinicalrecord: mapper, repositorio y casos de uso.
 * Los casos de uso no usan Spring; aquí se crean y se les entrega lo que necesitan.
 */
@Configuration
public class ClinicalRecordBeansConfig {

    @Bean
    public ClinicalRecordPersistenceMapper clinicalRecordPersistenceMapper() {
        return new ClinicalRecordPersistenceMapper();
    }

    @Bean
    public ClinicalRecordRepository clinicalRecordRepository(ClinicalRecordJpaRepository repository, ClinicalRecordPersistenceMapper mapper) {
        return new ClinicalRecordRepositoryAdapter(repository, mapper);
    }

    @Bean
    public RegisterClinicalRecordUseCase registerClinicalRecordUseCase(ClinicalRecordRepository repository) {
        return new RegisterClinicalRecordUseCase(repository);
    }

    @Bean
    public GetClinicalRecordByIdUseCase getClinicalRecordByIdUseCase(ClinicalRecordRepository repository) {
        return new GetClinicalRecordByIdUseCase(repository);
    }

    @Bean
    public ListClinicalRecordUseCase listClinicalRecordUseCase(ClinicalRecordRepository repository) {
        return new ListClinicalRecordUseCase(repository);
    }

    @Bean
    public UpdateClinicalRecordUseCase updateClinicalRecordUseCase(ClinicalRecordRepository repository) {
        return new UpdateClinicalRecordUseCase(repository);
    }

    @Bean
    public DeleteClinicalRecordUseCase deleteClinicalRecordUseCase(ClinicalRecordRepository repository) {
        return new DeleteClinicalRecordUseCase(repository);
    }
}
