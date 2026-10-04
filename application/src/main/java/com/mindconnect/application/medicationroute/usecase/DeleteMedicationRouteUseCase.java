package com.mindconnect.application.medicationroute.usecase;

import java.time.LocalDateTime;

import com.mindconnect.application.medicationroute.exception.MedicationRouteNotFoundApplicationException;
import com.mindconnect.domain.medicationroute.event.MedicationRouteDeletedEvent;
import com.mindconnect.domain.medicationroute.model.valueobject.MedicationRouteId;
import com.mindconnect.domain.medicationroute.port.repository.MedicationRouteRepository;

public class DeleteMedicationRouteUseCase {

    private final MedicationRouteRepository repository;

    public DeleteMedicationRouteUseCase(MedicationRouteRepository repository) {
        this.repository = repository;
    }

    public MedicationRouteDeletedEvent execute(MedicationRouteId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new MedicationRouteNotFoundApplicationException(id));

        repository.delete(aggregate);

        return new MedicationRouteDeletedEvent(id, LocalDateTime.now());
    }
}
