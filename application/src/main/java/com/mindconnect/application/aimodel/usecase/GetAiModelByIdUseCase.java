package com.mindconnect.application.aimodel.usecase;

import com.mindconnect.application.aimodel.dto.AiModelResponse;
import com.mindconnect.application.aimodel.exception.AiModelNotFoundApplicationException;
import com.mindconnect.domain.aimodel.model.valueobject.AiModelId;
import com.mindconnect.domain.aimodel.port.repository.AiModelRepository;

public class GetAiModelByIdUseCase {

    private final AiModelRepository repository;

    public GetAiModelByIdUseCase(AiModelRepository repository) {
        this.repository = repository;
    }

    public AiModelResponse execute(AiModelId id) {
        return repository.findById(id)
                .map(AiModelResponse::fromDomain)
                .orElseThrow(() -> new AiModelNotFoundApplicationException(id));
    }
}
