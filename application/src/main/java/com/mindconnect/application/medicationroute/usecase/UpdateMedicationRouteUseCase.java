package com.mindconnect.application.medicationroute.usecase;

import com.mindconnect.application.medicationroute.command.UpdateMedicationRouteCommand;
import com.mindconnect.application.medicationroute.dto.MedicationRouteResponse;
import com.mindconnect.application.medicationroute.exception.MedicationRouteNotFoundApplicationException;
import com.mindconnect.domain.medicationroute.model.aggregate.MedicationRoute;
import com.mindconnect.domain.medicationroute.port.repository.MedicationRouteRepository;

public class UpdateMedicationRouteUseCase {

    private final MedicationRouteRepository repository;

    public UpdateMedicationRouteUseCase(MedicationRouteRepository repository) {
        this.repository = repository;
    }

    public MedicationRouteResponse execute(UpdateMedicationRouteCommand command) {
        MedicationRoute aggregate = repository.findById(command.id())
                .orElseThrow(() -> new MedicationRouteNotFoundApplicationException(command.id()));
        aggregate.update(
                command.code(), command.name());
        MedicationRoute saved = repository.save(aggregate);
        return MedicationRouteResponse.fromDomain(saved);
    }
}
