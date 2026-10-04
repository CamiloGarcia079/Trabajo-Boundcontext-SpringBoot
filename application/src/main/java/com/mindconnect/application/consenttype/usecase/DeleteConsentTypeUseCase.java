package com.mindconnect.application.consenttype.usecase;

import java.time.LocalDateTime;

import com.mindconnect.application.consenttype.exception.ConsentTypeNotFoundApplicationException;
import com.mindconnect.domain.consenttype.event.ConsentTypeDeletedEvent;
import com.mindconnect.domain.consenttype.model.valueobject.ConsentTypeId;
import com.mindconnect.domain.consenttype.port.repository.ConsentTypeRepository;

public class DeleteConsentTypeUseCase {

    private final ConsentTypeRepository repository;

    public DeleteConsentTypeUseCase(ConsentTypeRepository repository) {
        this.repository = repository;
    }

    public ConsentTypeDeletedEvent execute(ConsentTypeId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new ConsentTypeNotFoundApplicationException(id));

        repository.delete(aggregate);

        return new ConsentTypeDeletedEvent(id, LocalDateTime.now());
    }
}
