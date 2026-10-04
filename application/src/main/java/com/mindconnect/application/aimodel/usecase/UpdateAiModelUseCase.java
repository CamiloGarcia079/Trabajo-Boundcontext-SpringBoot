package com.mindconnect.application.aimodel.usecase;

import com.mindconnect.application.aimodel.command.UpdateAiModelCommand;
import com.mindconnect.application.aimodel.dto.AiModelResponse;
import com.mindconnect.application.aimodel.exception.AiModelNotFoundApplicationException;
import com.mindconnect.domain.aimodel.model.aggregate.AiModel;
import com.mindconnect.domain.aimodel.port.repository.AiModelRepository;

public class UpdateAiModelUseCase {

    private final AiModelRepository repository;

    public UpdateAiModelUseCase(AiModelRepository repository) {
        this.repository = repository;
    }

    public AiModelResponse execute(UpdateAiModelCommand command) {
        AiModel aggregate = repository.findById(command.id())
                .orElseThrow(() -> new AiModelNotFoundApplicationException(command.id()));
        aggregate.update(
                command.providerModelId(), command.nameModel(), command.modelKey(), command.inputTokenPrice(), command.outputTokenPrice(), command.maxTokens(), command.contextWindow());
        AiModel saved = repository.save(aggregate);
        return AiModelResponse.fromDomain(saved);
    }
}
