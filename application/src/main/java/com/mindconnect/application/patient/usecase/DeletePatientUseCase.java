package com.mindconnect.application.patient.usecase;

import java.time.LocalDateTime;

import com.mindconnect.application.patient.exception.PatientNotFoundApplicationException;
import com.mindconnect.domain.patient.event.PatientDeletedEvent;
import com.mindconnect.domain.patient.model.valueobject.PatientId;
import com.mindconnect.domain.patient.port.repository.PatientRepository;

public class DeletePatientUseCase {

    private final PatientRepository repository;

    public DeletePatientUseCase(PatientRepository repository) {
        this.repository = repository;
    }

    public PatientDeletedEvent execute(PatientId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new PatientNotFoundApplicationException(id));

        repository.delete(aggregate);

        return new PatientDeletedEvent(id, LocalDateTime.now());
    }
}
