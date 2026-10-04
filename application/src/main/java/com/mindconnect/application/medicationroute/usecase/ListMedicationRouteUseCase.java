package com.mindconnect.application.medicationroute.usecase;

import java.util.List;

import com.mindconnect.application.medicationroute.dto.MedicationRouteResponse;
import com.mindconnect.domain.medicationroute.port.repository.MedicationRouteRepository;

public class ListMedicationRouteUseCase {

    private final MedicationRouteRepository repository;

    public ListMedicationRouteUseCase(MedicationRouteRepository repository) {
        this.repository = repository;
    }

    public List<MedicationRouteResponse> execute() {
        return repository.findAll()
                .stream()
                .map(MedicationRouteResponse::fromDomain)
                .toList();
    }
}
