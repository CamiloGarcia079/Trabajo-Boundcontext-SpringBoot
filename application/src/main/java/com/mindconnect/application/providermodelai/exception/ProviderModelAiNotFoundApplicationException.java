package com.mindconnect.application.providermodelai.exception;

import com.mindconnect.application.common.exception.ApplicationException;
import com.mindconnect.domain.providermodelai.model.valueobject.ProviderModelAiId;

public class ProviderModelAiNotFoundApplicationException extends ApplicationException {

    private static final long serialVersionUID = 1L;

    public ProviderModelAiNotFoundApplicationException(ProviderModelAiId id) {
        super("ProviderModelAi with id " + id.value() + " was not found.");
    }
}
