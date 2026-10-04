package com.mindconnect.application.patient.usecase;

import com.mindconnect.application.patient.dto.PatientResponse;
import com.mindconnect.application.patient.exception.PatientNotFoundApplicationException;
import com.mindconnect.domain.patient.model.valueobject.PatientId;
import com.mindconnect.domain.patient.port.repository.PatientRepository;

public class GetPatientByIdUseCase {

    private final PatientRepository repository;

    public GetPatientByIdUseCase(PatientRepository repository) {
        this.repository = repository;
    }

    public PatientResponse execute(PatientId id) {
        return repository.findById(id)
                .map(PatientResponse::fromDomain)
                .orElseThrow(() -> new PatientNotFoundApplicationException(id));
    }
}
