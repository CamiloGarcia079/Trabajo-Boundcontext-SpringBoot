package com.mindconnect.application.patientcontact.usecase;

import com.mindconnect.application.patientcontact.command.UpdatePatientContactCommand;
import com.mindconnect.application.patientcontact.dto.PatientContactResponse;
import com.mindconnect.application.patientcontact.exception.PatientContactNotFoundApplicationException;
import com.mindconnect.domain.patientcontact.model.aggregate.PatientContact;
import com.mindconnect.domain.patientcontact.port.repository.PatientContactRepository;

public class UpdatePatientContactUseCase {

    private final PatientContactRepository repository;

    public UpdatePatientContactUseCase(PatientContactRepository repository) {
        this.repository = repository;
    }

    public PatientContactResponse execute(UpdatePatientContactCommand command) {
        PatientContact aggregate = repository.findById(command.id())
                .orElseThrow(() -> new PatientContactNotFoundApplicationException(command.id()));
        aggregate.update(
                command.contactId(), command.patientId(), command.isPrimaryContact(), command.isEmergencyContact(), command.relationshipTypeId());
        PatientContact saved = repository.save(aggregate);
        return PatientContactResponse.fromDomain(saved);
    }
}
