package com.mindconnect.application.patientallergy.usecase;

import java.util.List;

import com.mindconnect.application.patientallergy.dto.PatientAllergyResponse;
import com.mindconnect.domain.patientallergy.port.repository.PatientAllergyRepository;

public class ListPatientAllergyUseCase {

    private final PatientAllergyRepository repository;

    public ListPatientAllergyUseCase(PatientAllergyRepository repository) {
        this.repository = repository;
    }

    public List<PatientAllergyResponse> execute() {
        return repository.findAll()
                .stream()
                .map(PatientAllergyResponse::fromDomain)
                .toList();
    }
}
