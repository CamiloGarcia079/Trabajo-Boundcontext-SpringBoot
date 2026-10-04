package com.mindconnect.application.providermodelai.usecase;

import com.mindconnect.application.providermodelai.command.UpdateProviderModelAiCommand;
import com.mindconnect.application.providermodelai.dto.ProviderModelAiResponse;
import com.mindconnect.application.providermodelai.exception.ProviderModelAiNotFoundApplicationException;
import com.mindconnect.domain.providermodelai.model.aggregate.ProviderModelAi;
import com.mindconnect.domain.providermodelai.port.repository.ProviderModelAiRepository;

public class UpdateProviderModelAiUseCase {

    private final ProviderModelAiRepository repository;

    public UpdateProviderModelAiUseCase(ProviderModelAiRepository repository) {
        this.repository = repository;
    }

    public ProviderModelAiResponse execute(UpdateProviderModelAiCommand command) {
        ProviderModelAi aggregate = repository.findById(command.id())
                .orElseThrow(() -> new ProviderModelAiNotFoundApplicationException(command.id()));
        aggregate.update(
                command.nameProviderAi(), command.razonSocial(), command.sitioWeb());
        ProviderModelAi saved = repository.save(aggregate);
        return ProviderModelAiResponse.fromDomain(saved);
    }
}
