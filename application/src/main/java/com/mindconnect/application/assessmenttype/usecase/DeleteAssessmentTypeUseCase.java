package com.mindconnect.application.assessmenttype.usecase;

import java.time.LocalDateTime;

import com.mindconnect.application.assessmenttype.exception.AssessmentTypeNotFoundApplicationException;
import com.mindconnect.domain.assessmenttype.event.AssessmentTypeDeletedEvent;
import com.mindconnect.domain.assessmenttype.model.valueobject.AssessmentTypeId;
import com.mindconnect.domain.assessmenttype.port.repository.AssessmentTypeRepository;

public class DeleteAssessmentTypeUseCase {

    private final AssessmentTypeRepository repository;

    public DeleteAssessmentTypeUseCase(AssessmentTypeRepository repository) {
        this.repository = repository;
    }

    public AssessmentTypeDeletedEvent execute(AssessmentTypeId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new AssessmentTypeNotFoundApplicationException(id));

        repository.delete(aggregate);

        return new AssessmentTypeDeletedEvent(id, LocalDateTime.now());
    }
}
