package com.mindconnect.application.professional.usecase;

import com.mindconnect.application.professional.command.UpdateProfessionalCommand;
import com.mindconnect.application.professional.dto.ProfessionalResponse;
import com.mindconnect.application.professional.exception.ProfessionalNotFoundApplicationException;
import com.mindconnect.domain.professional.model.aggregate.Professional;
import com.mindconnect.domain.professional.port.repository.ProfessionalRepository;

public class UpdateProfessionalUseCase {

    private final ProfessionalRepository repository;

    public UpdateProfessionalUseCase(ProfessionalRepository repository) {
        this.repository = repository;
    }

    public ProfessionalResponse execute(UpdateProfessionalCommand command) {
        Professional aggregate = repository.findById(command.id())
                .orElseThrow(() -> new ProfessionalNotFoundApplicationException(command.id()));
        aggregate.update(
                command.documentTypeId(), command.documentNumber(), command.firstName(), command.lastName(), command.professionalType(), command.licenseNumber(), command.cityId());
        Professional saved = repository.save(aggregate);
        return ProfessionalResponse.fromDomain(saved);
    }
}
