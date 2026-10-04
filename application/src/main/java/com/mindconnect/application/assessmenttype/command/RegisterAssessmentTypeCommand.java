package com.mindconnect.application.assessmenttype.command;


public record RegisterAssessmentTypeCommand(
        String code,
        String name,
        String description
) {
}
