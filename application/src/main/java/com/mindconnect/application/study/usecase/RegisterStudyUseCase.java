package com.mindconnect.application.study.usecase;

import com.mindconnect.application.study.command.RegisterStudyCommand;
import com.mindconnect.application.study.dto.StudyResponse;
import com.mindconnect.domain.study.model.aggregate.Study;
import com.mindconnect.domain.study.port.repository.StudyRepository;

public class RegisterStudyUseCase {

    private final StudyRepository repository;

    public RegisterStudyUseCase(StudyRepository repository) {
        this.repository = repository;
    }

    public StudyResponse execute(RegisterStudyCommand command) {
        Study aggregate = Study.register(
                command.name());
        Study saved = repository.save(aggregate);
        return StudyResponse.fromDomain(saved);
    }
}
