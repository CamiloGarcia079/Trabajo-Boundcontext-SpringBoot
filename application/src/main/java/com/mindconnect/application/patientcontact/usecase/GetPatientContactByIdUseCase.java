package com.mindconnect.application.patientcontact.usecase;

import com.mindconnect.application.patientcontact.dto.PatientContactResponse;
import com.mindconnect.application.patientcontact.exception.PatientContactNotFoundApplicationException;
import com.mindconnect.domain.patientcontact.model.valueobject.PatientContactId;
import com.mindconnect.domain.patientcontact.port.repository.PatientContactRepository;

public class GetPatientContactByIdUseCase {

    private final PatientContactRepository repository;

    public GetPatientContactByIdUseCase(PatientContactRepository repository) {
        this.repository = repository;
    }

    public PatientContactResponse execute(PatientContactId id) {
        return repository.findById(id)
                .map(PatientContactResponse::fromDomain)
                .orElseThrow(() -> new PatientContactNotFoundApplicationException(id));
    }
}
