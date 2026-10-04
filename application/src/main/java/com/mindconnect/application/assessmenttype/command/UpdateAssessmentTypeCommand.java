package com.mindconnect.application.assessmenttype.command;


import com.mindconnect.domain.assessmenttype.model.valueobject.AssessmentTypeId;

public record UpdateAssessmentTypeCommand(
        AssessmentTypeId id,
        String code,
        String name,
        String description
) {
}
