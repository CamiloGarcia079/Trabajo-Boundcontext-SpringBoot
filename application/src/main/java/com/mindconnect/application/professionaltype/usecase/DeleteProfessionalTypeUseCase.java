package com.mindconnect.application.professionaltype.usecase;

import java.time.LocalDateTime;

import com.mindconnect.application.professionaltype.exception.ProfessionalTypeNotFoundApplicationException;
import com.mindconnect.domain.professionaltype.event.ProfessionalTypeDeletedEvent;
import com.mindconnect.domain.professionaltype.model.valueobject.ProfessionalTypeId;
import com.mindconnect.domain.professionaltype.port.repository.ProfessionalTypeRepository;

public class DeleteProfessionalTypeUseCase {

    private final ProfessionalTypeRepository repository;

    public DeleteProfessionalTypeUseCase(ProfessionalTypeRepository repository) {
        this.repository = repository;
    }

    public ProfessionalTypeDeletedEvent execute(ProfessionalTypeId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new ProfessionalTypeNotFoundApplicationException(id));

        repository.delete(aggregate);

        return new ProfessionalTypeDeletedEvent(id, LocalDateTime.now());
    }
}
