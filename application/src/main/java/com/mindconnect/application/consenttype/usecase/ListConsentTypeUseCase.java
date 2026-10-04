package com.mindconnect.application.consenttype.usecase;

import java.util.List;

import com.mindconnect.application.consenttype.dto.ConsentTypeResponse;
import com.mindconnect.domain.consenttype.port.repository.ConsentTypeRepository;

public class ListConsentTypeUseCase {

    private final ConsentTypeRepository repository;

    public ListConsentTypeUseCase(ConsentTypeRepository repository) {
        this.repository = repository;
    }

    public List<ConsentTypeResponse> execute() {
        return repository.findAll()
                .stream()
                .map(ConsentTypeResponse::fromDomain)
                .toList();
    }
}
