package com.mindconnect.application.aimodel.usecase;

import com.mindconnect.application.aimodel.command.RegisterAiModelCommand;
import com.mindconnect.application.aimodel.dto.AiModelResponse;
import com.mindconnect.domain.aimodel.model.aggregate.AiModel;
import com.mindconnect.domain.aimodel.port.repository.AiModelRepository;

public class RegisterAiModelUseCase {

    private final AiModelRepository repository;

    public RegisterAiModelUseCase(AiModelRepository repository) {
        this.repository = repository;
    }

    public AiModelResponse execute(RegisterAiModelCommand command) {
        AiModel aggregate = AiModel.register(
                command.providerModelId(), command.nameModel(), command.modelKey(), command.inputTokenPrice(), command.outputTokenPrice(), command.maxTokens(), command.contextWindow());
        AiModel saved = repository.save(aggregate);
        return AiModelResponse.fromDomain(saved);
    }
}
