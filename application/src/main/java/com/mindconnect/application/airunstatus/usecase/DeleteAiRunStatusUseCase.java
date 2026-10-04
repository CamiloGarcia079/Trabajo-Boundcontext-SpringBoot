package com.mindconnect.application.airunstatus.usecase;

import java.time.LocalDateTime;

import com.mindconnect.application.airunstatus.exception.AiRunStatusNotFoundApplicationException;
import com.mindconnect.domain.airunstatus.event.AiRunStatusDeletedEvent;
import com.mindconnect.domain.airunstatus.model.valueobject.AiRunStatusId;
import com.mindconnect.domain.airunstatus.port.repository.AiRunStatusRepository;

public class DeleteAiRunStatusUseCase {

    private final AiRunStatusRepository repository;

    public DeleteAiRunStatusUseCase(AiRunStatusRepository repository) {
        this.repository = repository;
    }

    public AiRunStatusDeletedEvent execute(AiRunStatusId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new AiRunStatusNotFoundApplicationException(id));

        repository.delete(aggregate);

        return new AiRunStatusDeletedEvent(id, LocalDateTime.now());
    }
}
