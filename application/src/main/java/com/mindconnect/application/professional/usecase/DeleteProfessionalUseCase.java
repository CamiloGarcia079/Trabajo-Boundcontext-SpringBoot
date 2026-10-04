package com.mindconnect.application.professional.usecase;

import java.time.LocalDateTime;

import com.mindconnect.application.professional.exception.ProfessionalNotFoundApplicationException;
import com.mindconnect.domain.professional.event.ProfessionalDeletedEvent;
import com.mindconnect.domain.professional.model.valueobject.ProfessionalId;
import com.mindconnect.domain.professional.port.repository.ProfessionalRepository;

public class DeleteProfessionalUseCase {

    private final ProfessionalRepository repository;

    public DeleteProfessionalUseCase(ProfessionalRepository repository) {
        this.repository = repository;
    }

    public ProfessionalDeletedEvent execute(ProfessionalId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new ProfessionalNotFoundApplicationException(id));

        repository.delete(aggregate);

        return new ProfessionalDeletedEvent(id, LocalDateTime.now());
    }
}
