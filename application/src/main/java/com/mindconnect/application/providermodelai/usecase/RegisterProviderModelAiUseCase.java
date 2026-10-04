package com.mindconnect.application.providermodelai.usecase;

import com.mindconnect.application.providermodelai.command.RegisterProviderModelAiCommand;
import com.mindconnect.application.providermodelai.dto.ProviderModelAiResponse;
import com.mindconnect.domain.providermodelai.model.aggregate.ProviderModelAi;
import com.mindconnect.domain.providermodelai.port.repository.ProviderModelAiRepository;

public class RegisterProviderModelAiUseCase {

    private final ProviderModelAiRepository repository;

    public RegisterProviderModelAiUseCase(ProviderModelAiRepository repository) {
        this.repository = repository;
    }

    public ProviderModelAiResponse execute(RegisterProviderModelAiCommand command) {
        ProviderModelAi aggregate = ProviderModelAi.register(
                command.nameProviderAi(), command.razonSocial(), command.sitioWeb());
        ProviderModelAi saved = repository.save(aggregate);
        return ProviderModelAiResponse.fromDomain(saved);
    }
}
