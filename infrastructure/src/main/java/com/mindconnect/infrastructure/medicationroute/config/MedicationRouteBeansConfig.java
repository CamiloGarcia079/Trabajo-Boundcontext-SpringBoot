package com.mindconnect.infrastructure.medicationroute.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mindconnect.application.medicationroute.usecase.DeleteMedicationRouteUseCase;
import com.mindconnect.application.medicationroute.usecase.GetMedicationRouteByIdUseCase;
import com.mindconnect.application.medicationroute.usecase.ListMedicationRouteUseCase;
import com.mindconnect.application.medicationroute.usecase.RegisterMedicationRouteUseCase;
import com.mindconnect.application.medicationroute.usecase.UpdateMedicationRouteUseCase;
import com.mindconnect.domain.medicationroute.port.repository.MedicationRouteRepository;
import com.mindconnect.infrastructure.medicationroute.adapters.out.persistence.mappers.MedicationRoutePersistenceMapper;
import com.mindconnect.infrastructure.medicationroute.adapters.out.persistence.repositories.MedicationRouteJpaRepository;
import com.mindconnect.infrastructure.medicationroute.adapters.out.persistence.repositories.MedicationRouteRepositoryAdapter;

/**
 * Conecta las piezas del contexto medicationroute: mapper, repositorio y casos de uso.
 * Los casos de uso no usan Spring; aquí se crean y se les entrega lo que necesitan.
 */
@Configuration
public class MedicationRouteBeansConfig {

    @Bean
    public MedicationRoutePersistenceMapper medicationRoutePersistenceMapper() {
        return new MedicationRoutePersistenceMapper();
    }

    @Bean
    public MedicationRouteRepository medicationRouteRepository(MedicationRouteJpaRepository repository, MedicationRoutePersistenceMapper mapper) {
        return new MedicationRouteRepositoryAdapter(repository, mapper);
    }

    @Bean
    public RegisterMedicationRouteUseCase registerMedicationRouteUseCase(MedicationRouteRepository repository) {
        return new RegisterMedicationRouteUseCase(repository);
    }

    @Bean
    public GetMedicationRouteByIdUseCase getMedicationRouteByIdUseCase(MedicationRouteRepository repository) {
        return new GetMedicationRouteByIdUseCase(repository);
    }

    @Bean
    public ListMedicationRouteUseCase listMedicationRouteUseCase(MedicationRouteRepository repository) {
        return new ListMedicationRouteUseCase(repository);
    }

    @Bean
    public UpdateMedicationRouteUseCase updateMedicationRouteUseCase(MedicationRouteRepository repository) {
        return new UpdateMedicationRouteUseCase(repository);
    }

    @Bean
    public DeleteMedicationRouteUseCase deleteMedicationRouteUseCase(MedicationRouteRepository repository) {
        return new DeleteMedicationRouteUseCase(repository);
    }
}
