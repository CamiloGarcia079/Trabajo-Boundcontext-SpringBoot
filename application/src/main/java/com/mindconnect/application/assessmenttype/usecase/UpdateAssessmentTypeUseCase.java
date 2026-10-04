package com.mindconnect.application.assessmenttype.usecase;

import com.mindconnect.application.assessmenttype.command.UpdateAssessmentTypeCommand;
import com.mindconnect.application.assessmenttype.dto.AssessmentTypeResponse;
import com.mindconnect.application.assessmenttype.exception.AssessmentTypeNotFoundApplicationException;
import com.mindconnect.domain.assessmenttype.model.aggregate.AssessmentType;
import com.mindconnect.domain.assessmenttype.port.repository.AssessmentTypeRepository;

public class UpdateAssessmentTypeUseCase {

    private final AssessmentTypeRepository repository;

    public UpdateAssessmentTypeUseCase(AssessmentTypeRepository repository) {
        this.repository = repository;
    }

    public AssessmentTypeResponse execute(UpdateAssessmentTypeCommand command) {
        AssessmentType aggregate = repository.findById(command.id())
                .orElseThrow(() -> new AssessmentTypeNotFoundApplicationException(command.id()));
        aggregate.update(
                command.code(), command.name(), command.description());
        AssessmentType saved = repository.save(aggregate);
        return AssessmentTypeResponse.fromDomain(saved);
    }
}
