package com.mindconnect.application.professionaltype.usecase;

import com.mindconnect.application.professionaltype.command.RegisterProfessionalTypeCommand;
import com.mindconnect.application.professionaltype.dto.ProfessionalTypeResponse;
import com.mindconnect.domain.professionaltype.model.aggregate.ProfessionalType;
import com.mindconnect.domain.professionaltype.port.repository.ProfessionalTypeRepository;

public class RegisterProfessionalTypeUseCase {

    private final ProfessionalTypeRepository repository;

    public RegisterProfessionalTypeUseCase(ProfessionalTypeRepository repository) {
        this.repository = repository;
    }

    public ProfessionalTypeResponse execute(RegisterProfessionalTypeCommand command) {
        ProfessionalType aggregate = ProfessionalType.register(
                command.name());
        ProfessionalType saved = repository.save(aggregate);
        return ProfessionalTypeResponse.fromDomain(saved);
    }
}
