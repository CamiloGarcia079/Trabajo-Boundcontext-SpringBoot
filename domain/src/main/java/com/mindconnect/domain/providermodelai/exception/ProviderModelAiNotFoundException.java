package com.mindconnect.domain.providermodelai.exception;

import com.mindconnect.domain.common.exception.DomainException;
import com.mindconnect.domain.providermodelai.model.valueobject.ProviderModelAiId;

public class ProviderModelAiNotFoundException extends DomainException {

    private static final long serialVersionUID = 1L;

    public ProviderModelAiNotFoundException(ProviderModelAiId id) {
        super("ProviderModelAi with id " + id.value() + " was not found.");
    }
}
