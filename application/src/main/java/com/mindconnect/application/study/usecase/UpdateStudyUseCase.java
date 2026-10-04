package com.mindconnect.application.study.usecase;

import com.mindconnect.application.study.command.UpdateStudyCommand;
import com.mindconnect.application.study.dto.StudyResponse;
import com.mindconnect.application.study.exception.StudyNotFoundApplicationException;
import com.mindconnect.domain.study.model.aggregate.Study;
import com.mindconnect.domain.study.port.repository.StudyRepository;

public class UpdateStudyUseCase {

    private final StudyRepository repository;

    public UpdateStudyUseCase(StudyRepository repository) {
        this.repository = repository;
    }

    public StudyResponse execute(UpdateStudyCommand command) {
        Study aggregate = repository.findById(command.id())
                .orElseThrow(() -> new StudyNotFoundApplicationException(command.id()));
        aggregate.update(
                command.name());
        Study saved = repository.save(aggregate);
        return StudyResponse.fromDomain(saved);
    }
}
