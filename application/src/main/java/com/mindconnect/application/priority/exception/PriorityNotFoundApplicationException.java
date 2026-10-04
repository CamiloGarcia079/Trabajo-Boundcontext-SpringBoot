package com.mindconnect.application.priority.exception;

import com.mindconnect.application.common.exception.ApplicationException;
import com.mindconnect.domain.priority.model.valueobject.PriorityId;

public class PriorityNotFoundApplicationException extends ApplicationException {

    private static final long serialVersionUID = 1L;

    public PriorityNotFoundApplicationException(PriorityId id) {
        super("Priority with id " + id.value() + " was not found.");
    }
}
