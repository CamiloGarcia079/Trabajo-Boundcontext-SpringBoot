package com.mindconnect.application.consenttype.usecase;

import com.mindconnect.application.consenttype.command.RegisterConsentTypeCommand;
import com.mindconnect.application.consenttype.dto.ConsentTypeResponse;
import com.mindconnect.domain.consenttype.model.aggregate.ConsentType;
import com.mindconnect.domain.consenttype.port.repository.ConsentTypeRepository;

public class RegisterConsentTypeUseCase {

    private final ConsentTypeRepository repository;

    public RegisterConsentTypeUseCase(ConsentTypeRepository repository) {
        this.repository = repository;
    }

    public ConsentTypeResponse execute(RegisterConsentTypeCommand command) {
        ConsentType aggregate = ConsentType.register(
                command.code(), command.name(), command.description());
        ConsentType saved = repository.save(aggregate);
        return ConsentTypeResponse.fromDomain(saved);
    }
}
