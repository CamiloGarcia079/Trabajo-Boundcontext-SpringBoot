package com.mindconnect.application.patientallergy.usecase;

import com.mindconnect.application.patientallergy.command.RegisterPatientAllergyCommand;
import com.mindconnect.application.patientallergy.dto.PatientAllergyResponse;
import com.mindconnect.domain.patientallergy.model.aggregate.PatientAllergy;
import com.mindconnect.domain.patientallergy.port.repository.PatientAllergyRepository;

public class RegisterPatientAllergyUseCase {

    private final PatientAllergyRepository repository;

    public RegisterPatientAllergyUseCase(PatientAllergyRepository repository) {
        this.repository = repository;
    }

    public PatientAllergyResponse execute(RegisterPatientAllergyCommand command) {
        PatientAllergy aggregate = PatientAllergy.register(
                command.patientId(), command.substance(), command.reaction(), command.severity(), command.recordedAt(), command.recordedBy());
        PatientAllergy saved = repository.save(aggregate);
        return PatientAllergyResponse.fromDomain(saved);
    }
}
