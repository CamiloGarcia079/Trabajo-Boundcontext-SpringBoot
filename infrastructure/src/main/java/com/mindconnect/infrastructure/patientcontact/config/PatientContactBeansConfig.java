package com.mindconnect.infrastructure.patientcontact.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mindconnect.application.patientcontact.usecase.DeletePatientContactUseCase;
import com.mindconnect.application.patientcontact.usecase.GetPatientContactByIdUseCase;
import com.mindconnect.application.patientcontact.usecase.ListPatientContactUseCase;
import com.mindconnect.application.patientcontact.usecase.RegisterPatientContactUseCase;
import com.mindconnect.application.patientcontact.usecase.UpdatePatientContactUseCase;
import com.mindconnect.domain.patientcontact.port.repository.PatientContactRepository;
import com.mindconnect.infrastructure.patientcontact.adapters.out.persistence.mappers.PatientContactPersistenceMapper;
import com.mindconnect.infrastructure.patientcontact.adapters.out.persistence.repositories.PatientContactJpaRepository;
import com.mindconnect.infrastructure.patientcontact.adapters.out.persistence.repositories.PatientContactRepositoryAdapter;

/**
 * Conecta las piezas del contexto patientcontact: mapper, repositorio y casos de uso.
 * Los casos de uso no usan Spring; aquí se crean y se les entrega lo que necesitan.
 */
@Configuration
public class PatientContactBeansConfig {

    @Bean
    public PatientContactPersistenceMapper patientContactPersistenceMapper() {
        return new PatientContactPersistenceMapper();
    }

    @Bean
    public PatientContactRepository patientContactRepository(PatientContactJpaRepository repository, PatientContactPersistenceMapper mapper) {
        return new PatientContactRepositoryAdapter(repository, mapper);
    }

    @Bean
    public RegisterPatientContactUseCase registerPatientContactUseCase(PatientContactRepository repository) {
        return new RegisterPatientContactUseCase(repository);
    }

    @Bean
    public GetPatientContactByIdUseCase getPatientContactByIdUseCase(PatientContactRepository repository) {
        return new GetPatientContactByIdUseCase(repository);
    }

    @Bean
    public ListPatientContactUseCase listPatientContactUseCase(PatientContactRepository repository) {
        return new ListPatientContactUseCase(repository);
    }

    @Bean
    public UpdatePatientContactUseCase updatePatientContactUseCase(PatientContactRepository repository) {
        return new UpdatePatientContactUseCase(repository);
    }

    @Bean
    public DeletePatientContactUseCase deletePatientContactUseCase(PatientContactRepository repository) {
        return new DeletePatientContactUseCase(repository);
    }
}
