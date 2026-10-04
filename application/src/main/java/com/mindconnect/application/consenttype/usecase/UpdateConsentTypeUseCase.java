package com.mindconnect.application.consenttype.usecase;

import com.mindconnect.application.consenttype.command.UpdateConsentTypeCommand;
import com.mindconnect.application.consenttype.dto.ConsentTypeResponse;
import com.mindconnect.application.consenttype.exception.ConsentTypeNotFoundApplicationException;
import com.mindconnect.domain.consenttype.model.aggregate.ConsentType;
import com.mindconnect.domain.consenttype.port.repository.ConsentTypeRepository;

public class UpdateConsentTypeUseCase {

    private final ConsentTypeRepository repository;

    public UpdateConsentTypeUseCase(ConsentTypeRepository repository) {
        this.repository = repository;
    }

    public ConsentTypeResponse execute(UpdateConsentTypeCommand command) {
        ConsentType aggregate = repository.findById(command.id())
                .orElseThrow(() -> new ConsentTypeNotFoundApplicationException(command.id()));
        aggregate.update(
                command.code(), command.name(), command.description());
        ConsentType saved = repository.save(aggregate);
        return ConsentTypeResponse.fromDomain(saved);
    }
}
