package com.mindconnect.domain.aimodel.exception;

import com.mindconnect.domain.common.exception.DomainException;
import com.mindconnect.domain.aimodel.model.valueobject.AiModelId;

public class AiModelNotFoundException extends DomainException {

    private static final long serialVersionUID = 1L;

    public AiModelNotFoundException(AiModelId id) {
        super("AiModel with id " + id.value() + " was not found.");
    }
}
