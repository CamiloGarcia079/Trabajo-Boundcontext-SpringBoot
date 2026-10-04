package com.mindconnect.application.patientallergy.usecase;

import java.time.LocalDateTime;

import com.mindconnect.application.patientallergy.exception.PatientAllergyNotFoundApplicationException;
import com.mindconnect.domain.patientallergy.event.PatientAllergyDeletedEvent;
import com.mindconnect.domain.patientallergy.model.valueobject.PatientAllergyId;
import com.mindconnect.domain.patientallergy.port.repository.PatientAllergyRepository;

public class DeletePatientAllergyUseCase {

    private final PatientAllergyRepository repository;

    public DeletePatientAllergyUseCase(PatientAllergyRepository repository) {
        this.repository = repository;
    }

    public PatientAllergyDeletedEvent execute(PatientAllergyId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new PatientAllergyNotFoundApplicationException(id));

        repository.delete(aggregate);

        return new PatientAllergyDeletedEvent(id, LocalDateTime.now());
    }
}
