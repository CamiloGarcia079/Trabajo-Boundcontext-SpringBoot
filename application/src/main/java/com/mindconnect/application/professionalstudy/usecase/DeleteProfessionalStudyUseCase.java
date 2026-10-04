package com.mindconnect.application.professionalstudy.usecase;

import java.time.LocalDateTime;

import com.mindconnect.application.professionalstudy.exception.ProfessionalStudyNotFoundApplicationException;
import com.mindconnect.domain.professionalstudy.event.ProfessionalStudyDeletedEvent;
import com.mindconnect.domain.professionalstudy.model.valueobject.ProfessionalStudyId;
import com.mindconnect.domain.professionalstudy.port.repository.ProfessionalStudyRepository;

public class DeleteProfessionalStudyUseCase {

    private final ProfessionalStudyRepository repository;

    public DeleteProfessionalStudyUseCase(ProfessionalStudyRepository repository) {
        this.repository = repository;
    }

    public ProfessionalStudyDeletedEvent execute(ProfessionalStudyId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new ProfessionalStudyNotFoundApplicationException(id));

        repository.delete(aggregate);

        return new ProfessionalStudyDeletedEvent(id, LocalDateTime.now());
    }
}
