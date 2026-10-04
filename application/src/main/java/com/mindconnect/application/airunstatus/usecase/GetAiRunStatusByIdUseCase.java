package com.mindconnect.application.airunstatus.usecase;

import com.mindconnect.application.airunstatus.dto.AiRunStatusResponse;
import com.mindconnect.application.airunstatus.exception.AiRunStatusNotFoundApplicationException;
import com.mindconnect.domain.airunstatus.model.valueobject.AiRunStatusId;
import com.mindconnect.domain.airunstatus.port.repository.AiRunStatusRepository;

public class GetAiRunStatusByIdUseCase {

    private final AiRunStatusRepository repository;

    public GetAiRunStatusByIdUseCase(AiRunStatusRepository repository) {
        this.repository = repository;
    }

    public AiRunStatusResponse execute(AiRunStatusId id) {
        return repository.findById(id)
                .map(AiRunStatusResponse::fromDomain)
                .orElseThrow(() -> new AiRunStatusNotFoundApplicationException(id));
    }
}
