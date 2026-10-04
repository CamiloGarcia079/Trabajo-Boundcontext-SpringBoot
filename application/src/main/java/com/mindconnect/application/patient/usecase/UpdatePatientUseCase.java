package com.mindconnect.application.patient.usecase;

import com.mindconnect.application.patient.command.UpdatePatientCommand;
import com.mindconnect.application.patient.dto.PatientResponse;
import com.mindconnect.application.patient.exception.PatientNotFoundApplicationException;
import com.mindconnect.domain.patient.model.aggregate.Patient;
import com.mindconnect.domain.patient.port.repository.PatientRepository;

public class UpdatePatientUseCase {

    private final PatientRepository repository;

    public UpdatePatientUseCase(PatientRepository repository) {
        this.repository = repository;
    }

    public PatientResponse execute(UpdatePatientCommand command) {
        Patient aggregate = repository.findById(command.id())
                .orElseThrow(() -> new PatientNotFoundApplicationException(command.id()));
        aggregate.update(
                command.documentTypeId(), command.documentNumber(), command.firstName(), command.middleName(), command.lastName(), command.secondLastName(), command.birthDate(), command.biologicalSexId(), command.genderIdentity(), command.email(), command.phone(), command.address(), command.updatedBy(), command.cityId());
        Patient saved = repository.save(aggregate);
        return PatientResponse.fromDomain(saved);
    }
}
