package com.mindconnect.application.airunstatus.usecase;

import java.util.List;

import com.mindconnect.application.airunstatus.dto.AiRunStatusResponse;
import com.mindconnect.domain.airunstatus.port.repository.AiRunStatusRepository;

public class ListAiRunStatusUseCase {

    private final AiRunStatusRepository repository;

    public ListAiRunStatusUseCase(AiRunStatusRepository repository) {
        this.repository = repository;
    }

    public List<AiRunStatusResponse> execute() {
        return repository.findAll()
                .stream()
                .map(AiRunStatusResponse::fromDomain)
                .toList();
    }
}
