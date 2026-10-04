package com.mindconnect.domain.priority.exception;

import com.mindconnect.domain.common.exception.DomainException;
import com.mindconnect.domain.priority.model.valueobject.PriorityId;

public class PriorityNotFoundException extends DomainException {

    private static final long serialVersionUID = 1L;

    public PriorityNotFoundException(PriorityId id) {
        super("Priority with id " + id.value() + " was not found.");
    }
}
