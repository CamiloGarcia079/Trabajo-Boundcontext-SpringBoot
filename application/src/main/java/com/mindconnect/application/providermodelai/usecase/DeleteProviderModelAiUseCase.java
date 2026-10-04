package com.mindconnect.application.providermodelai.usecase;

import java.time.LocalDateTime;

import com.mindconnect.application.providermodelai.exception.ProviderModelAiNotFoundApplicationException;
import com.mindconnect.domain.providermodelai.event.ProviderModelAiDeletedEvent;
import com.mindconnect.domain.providermodelai.model.valueobject.ProviderModelAiId;
import com.mindconnect.domain.providermodelai.port.repository.ProviderModelAiRepository;

public class DeleteProviderModelAiUseCase {

    private final ProviderModelAiRepository repository;

    public DeleteProviderModelAiUseCase(ProviderModelAiRepository repository) {
        this.repository = repository;
    }

    public ProviderModelAiDeletedEvent execute(ProviderModelAiId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new ProviderModelAiNotFoundApplicationException(id));

        repository.delete(aggregate);

        return new ProviderModelAiDeletedEvent(id, LocalDateTime.now());
    }
}
