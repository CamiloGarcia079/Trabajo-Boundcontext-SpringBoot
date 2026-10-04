package com.mindconnect.application.aimodel.usecase;

import java.time.LocalDateTime;

import com.mindconnect.application.aimodel.exception.AiModelNotFoundApplicationException;
import com.mindconnect.domain.aimodel.event.AiModelDeletedEvent;
import com.mindconnect.domain.aimodel.model.valueobject.AiModelId;
import com.mindconnect.domain.aimodel.port.repository.AiModelRepository;

public class DeleteAiModelUseCase {

    private final AiModelRepository repository;

    public DeleteAiModelUseCase(AiModelRepository repository) {
        this.repository = repository;
    }

    public AiModelDeletedEvent execute(AiModelId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new AiModelNotFoundApplicationException(id));

        repository.delete(aggregate);

        return new AiModelDeletedEvent(id, LocalDateTime.now());
    }
}
