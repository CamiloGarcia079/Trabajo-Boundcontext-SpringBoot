package com.mindconnect.application.study.usecase;

import java.time.LocalDateTime;

import com.mindconnect.application.study.exception.StudyNotFoundApplicationException;
import com.mindconnect.domain.study.event.StudyDeletedEvent;
import com.mindconnect.domain.study.model.valueobject.StudyId;
import com.mindconnect.domain.study.port.repository.StudyRepository;

public class DeleteStudyUseCase {

    private final StudyRepository repository;

    public DeleteStudyUseCase(StudyRepository repository) {
        this.repository = repository;
    }

    public StudyDeletedEvent execute(StudyId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new StudyNotFoundApplicationException(id));

        repository.delete(aggregate);

        return new StudyDeletedEvent(id, LocalDateTime.now());
    }
}
