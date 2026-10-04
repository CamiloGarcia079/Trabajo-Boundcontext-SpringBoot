package com.mindconnect.domain.airunstatus.exception;

import com.mindconnect.domain.common.exception.DomainException;
import com.mindconnect.domain.airunstatus.model.valueobject.AiRunStatusId;

public class AiRunStatusNotFoundException extends DomainException {

    private static final long serialVersionUID = 1L;

    public AiRunStatusNotFoundException(AiRunStatusId id) {
        super("AiRunStatus with id " + id.value() + " was not found.");
    }
}
