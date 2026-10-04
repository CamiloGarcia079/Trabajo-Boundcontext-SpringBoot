package com.mindconnect.application.consenttype.usecase;

import com.mindconnect.application.consenttype.dto.ConsentTypeResponse;
import com.mindconnect.application.consenttype.exception.ConsentTypeNotFoundApplicationException;
import com.mindconnect.domain.consenttype.model.valueobject.ConsentTypeId;
import com.mindconnect.domain.consenttype.port.repository.ConsentTypeRepository;

public class GetConsentTypeByIdUseCase {

    private final ConsentTypeRepository repository;

    public GetConsentTypeByIdUseCase(ConsentTypeRepository repository) {
        this.repository = repository;
    }

    public ConsentTypeResponse execute(ConsentTypeId id) {
        return repository.findById(id)
                .map(ConsentTypeResponse::fromDomain)
                .orElseThrow(() -> new ConsentTypeNotFoundApplicationException(id));
    }
}
