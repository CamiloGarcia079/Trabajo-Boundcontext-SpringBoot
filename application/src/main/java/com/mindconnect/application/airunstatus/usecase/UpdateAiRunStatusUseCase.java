package com.mindconnect.application.airunstatus.usecase;

import com.mindconnect.application.airunstatus.command.UpdateAiRunStatusCommand;
import com.mindconnect.application.airunstatus.dto.AiRunStatusResponse;
import com.mindconnect.application.airunstatus.exception.AiRunStatusNotFoundApplicationException;
import com.mindconnect.domain.airunstatus.model.aggregate.AiRunStatus;
import com.mindconnect.domain.airunstatus.port.repository.AiRunStatusRepository;

public class UpdateAiRunStatusUseCase {

    private final AiRunStatusRepository repository;

    public UpdateAiRunStatusUseCase(AiRunStatusRepository repository) {
        this.repository = repository;
    }

    public AiRunStatusResponse execute(UpdateAiRunStatusCommand command) {
        AiRunStatus aggregate = repository.findById(command.id())
                .orElseThrow(() -> new AiRunStatusNotFoundApplicationException(command.id()));
        aggregate.update(
                command.nameStatus());
        AiRunStatus saved = repository.save(aggregate);
        return AiRunStatusResponse.fromDomain(saved);
    }
}
