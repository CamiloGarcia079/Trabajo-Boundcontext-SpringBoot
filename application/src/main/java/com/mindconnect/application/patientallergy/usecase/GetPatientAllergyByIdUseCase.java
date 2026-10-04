package com.mindconnect.application.patientallergy.usecase;

import com.mindconnect.application.patientallergy.dto.PatientAllergyResponse;
import com.mindconnect.application.patientallergy.exception.PatientAllergyNotFoundApplicationException;
import com.mindconnect.domain.patientallergy.model.valueobject.PatientAllergyId;
import com.mindconnect.domain.patientallergy.port.repository.PatientAllergyRepository;

public class GetPatientAllergyByIdUseCase {

    private final PatientAllergyRepository repository;

    public GetPatientAllergyByIdUseCase(PatientAllergyRepository repository) {
        this.repository = repository;
    }

    public PatientAllergyResponse execute(PatientAllergyId id) {
        return repository.findById(id)
                .map(PatientAllergyResponse::fromDomain)
                .orElseThrow(() -> new PatientAllergyNotFoundApplicationException(id));
    }
}
