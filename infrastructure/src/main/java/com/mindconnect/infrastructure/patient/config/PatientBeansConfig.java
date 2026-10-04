package com.mindconnect.infrastructure.patient.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mindconnect.application.patient.usecase.DeletePatientUseCase;
import com.mindconnect.application.patient.usecase.GetPatientByIdUseCase;
import com.mindconnect.application.patient.usecase.ListPatientUseCase;
import com.mindconnect.application.patient.usecase.RegisterPatientUseCase;
import com.mindconnect.application.patient.usecase.UpdatePatientUseCase;
import com.mindconnect.domain.patient.port.repository.PatientRepository;
import com.mindconnect.infrastructure.patient.adapters.out.persistence.mappers.PatientPersistenceMapper;
import com.mindconnect.infrastructure.patient.adapters.out.persistence.repositories.PatientJpaRepository;
import com.mindconnect.infrastructure.patient.adapters.out.persistence.repositories.PatientRepositoryAdapter;

/**
 * Conecta las piezas del contexto patient: mapper, repositorio y casos de uso.
 * Los casos de uso no usan Spring; aquí se crean y se les entrega lo que necesitan.
 */
@Configuration
public class PatientBeansConfig {

    @Bean
    public PatientPersistenceMapper patientPersistenceMapper() {
        return new PatientPersistenceMapper();
    }

    @Bean
    public PatientRepository patientRepository(PatientJpaRepository repository, PatientPersistenceMapper mapper) {
        return new PatientRepositoryAdapter(repository, mapper);
    }

    @Bean
    public RegisterPatientUseCase registerPatientUseCase(PatientRepository repository) {
        return new RegisterPatientUseCase(repository);
    }

    @Bean
    public GetPatientByIdUseCase getPatientByIdUseCase(PatientRepository repository) {
        return new GetPatientByIdUseCase(repository);
    }

    @Bean
    public ListPatientUseCase listPatientUseCase(PatientRepository repository) {
        return new ListPatientUseCase(repository);
    }

    @Bean
    public UpdatePatientUseCase updatePatientUseCase(PatientRepository repository) {
        return new UpdatePatientUseCase(repository);
    }

    @Bean
    public DeletePatientUseCase deletePatientUseCase(PatientRepository repository) {
        return new DeletePatientUseCase(repository);
    }
}
