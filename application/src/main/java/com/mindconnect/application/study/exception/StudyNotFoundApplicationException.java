package com.mindconnect.application.study.exception;

import com.mindconnect.application.common.exception.ApplicationException;
import com.mindconnect.domain.study.model.valueobject.StudyId;

public class StudyNotFoundApplicationException extends ApplicationException {

    private static final long serialVersionUID = 1L;

    public StudyNotFoundApplicationException(StudyId id) {
        super("Study with id " + id.value() + " was not found.");
    }
}
