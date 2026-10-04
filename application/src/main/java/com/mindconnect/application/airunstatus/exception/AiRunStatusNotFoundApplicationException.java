package com.mindconnect.application.airunstatus.exception;

import com.mindconnect.application.common.exception.ApplicationException;
import com.mindconnect.domain.airunstatus.model.valueobject.AiRunStatusId;

public class AiRunStatusNotFoundApplicationException extends ApplicationException {

    private static final long serialVersionUID = 1L;

    public AiRunStatusNotFoundApplicationException(AiRunStatusId id) {
        super("AiRunStatus with id " + id.value() + " was not found.");
    }
}
