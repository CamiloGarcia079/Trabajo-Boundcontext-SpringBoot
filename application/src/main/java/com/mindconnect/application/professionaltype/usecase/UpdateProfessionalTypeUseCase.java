package com.mindconnect.application.professionaltype.usecase;

import com.mindconnect.application.professionaltype.command.UpdateProfessionalTypeCommand;
import com.mindconnect.application.professionaltype.dto.ProfessionalTypeResponse;
import com.mindconnect.application.professionaltype.exception.ProfessionalTypeNotFoundApplicationException;
import com.mindconnect.domain.professionaltype.model.aggregate.ProfessionalType;
import com.mindconnect.domain.professionaltype.port.repository.ProfessionalTypeRepository;

public class UpdateProfessionalTypeUseCase {

    private final ProfessionalTypeRepository repository;

    public UpdateProfessionalTypeUseCase(ProfessionalTypeRepository repository) {
        this.repository = repository;
    }

    public ProfessionalTypeResponse execute(UpdateProfessionalTypeCommand command) {
        ProfessionalType aggregate = repository.findById(command.id())
                .orElseThrow(() -> new ProfessionalTypeNotFoundApplicationException(command.id()));
        aggregate.update(
                command.name());
        ProfessionalType saved = repository.save(aggregate);
        return ProfessionalTypeResponse.fromDomain(saved);
    }
}
