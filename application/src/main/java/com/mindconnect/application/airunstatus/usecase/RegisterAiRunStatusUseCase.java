package com.mindconnect.application.airunstatus.usecase;

import com.mindconnect.application.airunstatus.command.RegisterAiRunStatusCommand;
import com.mindconnect.application.airunstatus.dto.AiRunStatusResponse;
import com.mindconnect.domain.airunstatus.model.aggregate.AiRunStatus;
import com.mindconnect.domain.airunstatus.port.repository.AiRunStatusRepository;

public class RegisterAiRunStatusUseCase {

    private final AiRunStatusRepository repository;

    public RegisterAiRunStatusUseCase(AiRunStatusRepository repository) {
        this.repository = repository;
    }

    public AiRunStatusResponse execute(RegisterAiRunStatusCommand command) {
        AiRunStatus aggregate = AiRunStatus.register(
                command.nameStatus());
        AiRunStatus saved = repository.save(aggregate);
        return AiRunStatusResponse.fromDomain(saved);
    }
}
