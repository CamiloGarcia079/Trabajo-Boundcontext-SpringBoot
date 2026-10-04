package com.mindconnect.application.study.command;


import com.mindconnect.domain.study.model.valueobject.StudyId;

public record UpdateStudyCommand(
        StudyId id,
        String name
) {
}
