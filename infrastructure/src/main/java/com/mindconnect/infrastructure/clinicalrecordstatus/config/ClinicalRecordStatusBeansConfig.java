package com.mindconnect.infrastructure.clinicalrecordstatus.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mindconnect.application.clinicalrecordstatus.usecase.DeleteClinicalRecordStatusUseCase;
import com.mindconnect.application.clinicalrecordstatus.usecase.GetClinicalRecordStatusByIdUseCase;
import com.mindconnect.application.clinicalrecordstatus.usecase.ListClinicalRecordStatusUseCase;
import com.mindconnect.application.clinicalrecordstatus.usecase.RegisterClinicalRecordStatusUseCase;
import com.mindconnect.application.clinicalrecordstatus.usecase.UpdateClinicalRecordStatusUseCase;
import com.mindconnect.domain.clinicalrecordstatus.port.repository.ClinicalRecordStatusRepository;
import com.mindconnect.infrastructure.clinicalrecordstatus.adapters.out.persistence.mappers.ClinicalRecordStatusPersistenceMapper;
import com.mindconnect.infrastructure.clinicalrecordstatus.adapters.out.persistence.repositories.ClinicalRecordStatusJpaRepository;
import com.mindconnect.infrastructure.clinicalrecordstatus.adapters.out.persistence.repositories.ClinicalRecordStatusRepositoryAdapter;

/**
 * Conecta las piezas del contexto clinicalrecordstatus: mapper, repositorio y casos de uso.
 * Los casos de uso no usan Spring; aquí se crean y se les entrega lo que necesitan.
 */
@Configuration
public class ClinicalRecordStatusBeansConfig {

    @Bean
    public ClinicalRecordStatusPersistenceMapper clinicalRecordStatusPersistenceMapper() {
        return new ClinicalRecordStatusPersistenceMapper();
    }

    @Bean
    public ClinicalRecordStatusRepository clinicalRecordStatusRepository(ClinicalRecordStatusJpaRepository repository, ClinicalRecordStatusPersistenceMapper mapper) {
        return new ClinicalRecordStatusRepositoryAdapter(repository, mapper);
    }

    @Bean
    public RegisterClinicalRecordStatusUseCase registerClinicalRecordStatusUseCase(ClinicalRecordStatusRepository repository) {
        return new RegisterClinicalRecordStatusUseCase(repository);
    }

    @Bean
    public GetClinicalRecordStatusByIdUseCase getClinicalRecordStatusByIdUseCase(ClinicalRecordStatusRepository repository) {
        return new GetClinicalRecordStatusByIdUseCase(repository);
    }

    @Bean
    public ListClinicalRecordStatusUseCase listClinicalRecordStatusUseCase(ClinicalRecordStatusRepository repository) {
        return new ListClinicalRecordStatusUseCase(repository);
    }

    @Bean
    public UpdateClinicalRecordStatusUseCase updateClinicalRecordStatusUseCase(ClinicalRecordStatusRepository repository) {
        return new UpdateClinicalRecordStatusUseCase(repository);
    }

    @Bean
    public DeleteClinicalRecordStatusUseCase deleteClinicalRecordStatusUseCase(ClinicalRecordStatusRepository repository) {
        return new DeleteClinicalRecordStatusUseCase(repository);
    }
}
