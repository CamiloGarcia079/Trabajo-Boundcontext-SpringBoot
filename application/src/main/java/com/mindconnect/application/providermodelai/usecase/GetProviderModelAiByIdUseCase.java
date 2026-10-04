package com.mindconnect.application.providermodelai.usecase;

import com.mindconnect.application.providermodelai.dto.ProviderModelAiResponse;
import com.mindconnect.application.providermodelai.exception.ProviderModelAiNotFoundApplicationException;
import com.mindconnect.domain.providermodelai.model.valueobject.ProviderModelAiId;
import com.mindconnect.domain.providermodelai.port.repository.ProviderModelAiRepository;

public class GetProviderModelAiByIdUseCase {

    private final ProviderModelAiRepository repository;

    public GetProviderModelAiByIdUseCase(ProviderModelAiRepository repository) {
        this.repository = repository;
    }

    public ProviderModelAiResponse execute(ProviderModelAiId id) {
        return repository.findById(id)
                .map(ProviderModelAiResponse::fromDomain)
                .orElseThrow(() -> new ProviderModelAiNotFoundApplicationException(id));
    }
}
