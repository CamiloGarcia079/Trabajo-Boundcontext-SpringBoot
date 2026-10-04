package com.mindconnect.domain.study.exception;

import com.mindconnect.domain.common.exception.DomainException;
import com.mindconnect.domain.study.model.valueobject.StudyId;

public class StudyNotFoundException extends DomainException {

    private static final long serialVersionUID = 1L;

    public StudyNotFoundException(StudyId id) {
        super("Study with id " + id.value() + " was not found.");
    }
}
