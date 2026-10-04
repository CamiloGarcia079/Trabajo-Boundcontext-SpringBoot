package com.mindconnect.application.patient.usecase;

import com.mindconnect.application.patient.command.RegisterPatientCommand;
import com.mindconnect.application.patient.dto.PatientResponse;
import com.mindconnect.domain.patient.model.aggregate.Patient;
import com.mindconnect.domain.patient.port.repository.PatientRepository;

public class RegisterPatientUseCase {

    private final PatientRepository repository;

    public RegisterPatientUseCase(PatientRepository repository) {
        this.repository = repository;
    }

    public PatientResponse execute(RegisterPatientCommand command) {
        Patient aggregate = Patient.register(
                command.documentTypeId(), command.documentNumber(), command.firstName(), command.middleName(), command.lastName(), command.secondLastName(), command.birthDate(), command.biologicalSexId(), command.genderIdentity(), command.email(), command.phone(), command.address(), command.createdBy(), command.updatedBy(), command.cityId());
        Patient saved = repository.save(aggregate);
        return PatientResponse.fromDomain(saved);
    }
}
