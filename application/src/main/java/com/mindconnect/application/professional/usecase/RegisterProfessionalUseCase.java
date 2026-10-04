package com.mindconnect.application.professional.usecase;

import com.mindconnect.application.professional.command.RegisterProfessionalCommand;
import com.mindconnect.application.professional.dto.ProfessionalResponse;
import com.mindconnect.domain.professional.model.aggregate.Professional;
import com.mindconnect.domain.professional.port.repository.ProfessionalRepository;

public class RegisterProfessionalUseCase {

    private final ProfessionalRepository repository;

    public RegisterProfessionalUseCase(ProfessionalRepository repository) {
        this.repository = repository;
    }

    public ProfessionalResponse execute(RegisterProfessionalCommand command) {
        Professional aggregate = Professional.register(
                command.documentTypeId(), command.documentNumber(), command.firstName(), command.lastName(), command.professionalType(), command.licenseNumber(), command.cityId());
        Professional saved = repository.save(aggregate);
        return ProfessionalResponse.fromDomain(saved);
    }
}
