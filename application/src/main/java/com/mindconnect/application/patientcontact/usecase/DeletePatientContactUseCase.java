package com.mindconnect.application.patientcontact.usecase;

import java.time.LocalDateTime;

import com.mindconnect.application.patientcontact.exception.PatientContactNotFoundApplicationException;
import com.mindconnect.domain.patientcontact.event.PatientContactDeletedEvent;
import com.mindconnect.domain.patientcontact.model.valueobject.PatientContactId;
import com.mindconnect.domain.patientcontact.port.repository.PatientContactRepository;

public class DeletePatientContactUseCase {

    private final PatientContactRepository repository;

    public DeletePatientContactUseCase(PatientContactRepository repository) {
        this.repository = repository;
    }

    public PatientContactDeletedEvent execute(PatientContactId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new PatientContactNotFoundApplicationException(id));

        repository.delete(aggregate);

        return new PatientContactDeletedEvent(id, LocalDateTime.now());
    }
}
