package com.mindconnect.application.aimodel.exception;

import com.mindconnect.application.common.exception.ApplicationException;
import com.mindconnect.domain.aimodel.model.valueobject.AiModelId;

public class AiModelNotFoundApplicationException extends ApplicationException {

    private static final long serialVersionUID = 1L;

    public AiModelNotFoundApplicationException(AiModelId id) {
        super("AiModel with id " + id.value() + " was not found.");
    }
}
