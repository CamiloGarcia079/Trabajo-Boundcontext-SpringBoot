package com.mindconnect.application.study.usecase;

import com.mindconnect.application.study.dto.StudyResponse;
import com.mindconnect.application.study.exception.StudyNotFoundApplicationException;
import com.mindconnect.domain.study.model.valueobject.StudyId;
import com.mindconnect.domain.study.port.repository.StudyRepository;

public class GetStudyByIdUseCase {

    private final StudyRepository repository;

    public GetStudyByIdUseCase(StudyRepository repository) {
        this.repository = repository;
    }

    public StudyResponse execute(StudyId id) {
        return repository.findById(id)
                .map(StudyResponse::fromDomain)
                .orElseThrow(() -> new StudyNotFoundApplicationException(id));
    }
}
