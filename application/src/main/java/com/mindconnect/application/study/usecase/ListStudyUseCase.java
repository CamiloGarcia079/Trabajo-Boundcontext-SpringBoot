package com.mindconnect.application.study.usecase;

import java.util.List;

import com.mindconnect.application.study.dto.StudyResponse;
import com.mindconnect.domain.study.port.repository.StudyRepository;

public class ListStudyUseCase {

    private final StudyRepository repository;

    public ListStudyUseCase(StudyRepository repository) {
        this.repository = repository;
    }

    public List<StudyResponse> execute() {
        return repository.findAll()
                .stream()
                .map(StudyResponse::fromDomain)
                .toList();
    }
}
