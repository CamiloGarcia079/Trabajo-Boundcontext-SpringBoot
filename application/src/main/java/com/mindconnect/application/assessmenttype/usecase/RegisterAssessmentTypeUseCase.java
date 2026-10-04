package com.mindconnect.application.assessmenttype.usecase;

import com.mindconnect.application.assessmenttype.command.RegisterAssessmentTypeCommand;
import com.mindconnect.application.assessmenttype.dto.AssessmentTypeResponse;
import com.mindconnect.domain.assessmenttype.model.aggregate.AssessmentType;
import com.mindconnect.domain.assessmenttype.port.repository.AssessmentTypeRepository;

public class RegisterAssessmentTypeUseCase {

    private final AssessmentTypeRepository repository;

    public RegisterAssessmentTypeUseCase(AssessmentTypeRepository repository) {
        this.repository = repository;
    }

    public AssessmentTypeResponse execute(RegisterAssessmentTypeCommand command) {
        AssessmentType aggregate = AssessmentType.register(
                command.code(), command.name(), command.description());
        AssessmentType saved = repository.save(aggregate);
        return AssessmentTypeResponse.fromDomain(saved);
    }
}
