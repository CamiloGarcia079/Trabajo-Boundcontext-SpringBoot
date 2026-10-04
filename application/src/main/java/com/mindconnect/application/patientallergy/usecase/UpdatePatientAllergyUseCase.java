package com.mindconnect.application.patientallergy.usecase;

import com.mindconnect.application.patientallergy.command.UpdatePatientAllergyCommand;
import com.mindconnect.application.patientallergy.dto.PatientAllergyResponse;
import com.mindconnect.application.patientallergy.exception.PatientAllergyNotFoundApplicationException;
import com.mindconnect.domain.patientallergy.model.aggregate.PatientAllergy;
import com.mindconnect.domain.patientallergy.port.repository.PatientAllergyRepository;

public class UpdatePatientAllergyUseCase {

    private final PatientAllergyRepository repository;

    public UpdatePatientAllergyUseCase(PatientAllergyRepository repository) {
        this.repository = repository;
    }

    public PatientAllergyResponse execute(UpdatePatientAllergyCommand command) {
        PatientAllergy aggregate = repository.findById(command.id())
                .orElseThrow(() -> new PatientAllergyNotFoundApplicationException(command.id()));
        aggregate.update(
                command.patientId(), command.substance(), command.reaction(), command.severity(), command.recordedAt(), command.recordedBy());
        PatientAllergy saved = repository.save(aggregate);
        return PatientAllergyResponse.fromDomain(saved);
    }
}
