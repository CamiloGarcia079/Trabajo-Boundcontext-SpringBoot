package com.mindconnect.application.assessmenttype.usecase;

import com.mindconnect.application.assessmenttype.dto.AssessmentTypeResponse;
import com.mindconnect.application.assessmenttype.exception.AssessmentTypeNotFoundApplicationException;
import com.mindconnect.domain.assessmenttype.model.valueobject.AssessmentTypeId;
import com.mindconnect.domain.assessmenttype.port.repository.AssessmentTypeRepository;

public class GetAssessmentTypeByIdUseCase {

    private final AssessmentTypeRepository repository;

    public GetAssessmentTypeByIdUseCase(AssessmentTypeRepository repository) {
        this.repository = repository;
    }

    public AssessmentTypeResponse execute(AssessmentTypeId id) {
        return repository.findById(id)
                .map(AssessmentTypeResponse::fromDomain)
                .orElseThrow(() -> new AssessmentTypeNotFoundApplicationException(id));
    }
}
