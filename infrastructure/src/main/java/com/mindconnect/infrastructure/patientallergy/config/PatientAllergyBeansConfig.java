package com.mindconnect.infrastructure.patientallergy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mindconnect.application.patientallergy.usecase.DeletePatientAllergyUseCase;
import com.mindconnect.application.patientallergy.usecase.GetPatientAllergyByIdUseCase;
import com.mindconnect.application.patientallergy.usecase.ListPatientAllergyUseCase;
import com.mindconnect.application.patientallergy.usecase.RegisterPatientAllergyUseCase;
import com.mindconnect.application.patientallergy.usecase.UpdatePatientAllergyUseCase;
import com.mindconnect.domain.patientallergy.port.repository.PatientAllergyRepository;
import com.mindconnect.infrastructure.patientallergy.adapters.out.persistence.mappers.PatientAllergyPersistenceMapper;
import com.mindconnect.infrastructure.patientallergy.adapters.out.persistence.repositories.PatientAllergyJpaRepository;
import com.mindconnect.infrastructure.patientallergy.adapters.out.persistence.repositories.PatientAllergyRepositoryAdapter;

/**
 * Conecta las piezas del contexto patientallergy: mapper, repositorio y casos de uso.
 * Los casos de uso no usan Spring; aquí se crean y se les entrega lo que necesitan.
 */
@Configuration
public class PatientAllergyBeansConfig {

    @Bean
    public PatientAllergyPersistenceMapper patientAllergyPersistenceMapper() {
        return new PatientAllergyPersistenceMapper();
    }

    @Bean
    public PatientAllergyRepository patientAllergyRepository(PatientAllergyJpaRepository repository, PatientAllergyPersistenceMapper mapper) {
        return new PatientAllergyRepositoryAdapter(repository, mapper);
    }

    @Bean
    public RegisterPatientAllergyUseCase registerPatientAllergyUseCase(PatientAllergyRepository repository) {
        return new RegisterPatientAllergyUseCase(repository);
    }

    @Bean
    public GetPatientAllergyByIdUseCase getPatientAllergyByIdUseCase(PatientAllergyRepository repository) {
        return new GetPatientAllergyByIdUseCase(repository);
    }

    @Bean
    public ListPatientAllergyUseCase listPatientAllergyUseCase(PatientAllergyRepository repository) {
        return new ListPatientAllergyUseCase(repository);
    }

    @Bean
    public UpdatePatientAllergyUseCase updatePatientAllergyUseCase(PatientAllergyRepository repository) {
        return new UpdatePatientAllergyUseCase(repository);
    }

    @Bean
    public DeletePatientAllergyUseCase deletePatientAllergyUseCase(PatientAllergyRepository repository) {
        return new DeletePatientAllergyUseCase(repository);
    }
}
