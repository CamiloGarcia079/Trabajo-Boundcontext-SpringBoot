package com.mindconnect.application.medicationroute.usecase;

import com.mindconnect.application.medicationroute.dto.MedicationRouteResponse;
import com.mindconnect.application.medicationroute.exception.MedicationRouteNotFoundApplicationException;
import com.mindconnect.domain.medicationroute.model.valueobject.MedicationRouteId;
import com.mindconnect.domain.medicationroute.port.repository.MedicationRouteRepository;

public class GetMedicationRouteByIdUseCase {

    private final MedicationRouteRepository repository;

    public GetMedicationRouteByIdUseCase(MedicationRouteRepository repository) {
        this.repository = repository;
    }

    public MedicationRouteResponse execute(MedicationRouteId id) {
        return repository.findById(id)
                .map(MedicationRouteResponse::fromDomain)
                .orElseThrow(() -> new MedicationRouteNotFoundApplicationException(id));
    }
}
