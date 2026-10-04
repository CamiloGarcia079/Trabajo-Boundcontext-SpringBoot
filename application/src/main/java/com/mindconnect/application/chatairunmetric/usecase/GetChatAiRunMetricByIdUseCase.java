package com.mindconnect.application.chatairunmetric.usecase;

import com.mindconnect.application.chatairunmetric.dto.ChatAiRunMetricResponse;
import com.mindconnect.application.chatairunmetric.exception.ChatAiRunMetricNotFoundApplicationException;
import com.mindconnect.domain.chatairunmetric.model.valueobject.ChatAiRunMetricId;
import com.mindconnect.domain.chatairunmetric.port.repository.ChatAiRunMetricRepository;

public class GetChatAiRunMetricByIdUseCase {

    private final ChatAiRunMetricRepository repository;

    public GetChatAiRunMetricByIdUseCase(ChatAiRunMetricRepository repository) {
        this.repository = repository;
    }

    public ChatAiRunMetricResponse execute(ChatAiRunMetricId id) {
        return repository.findById(id)
                .map(ChatAiRunMetricResponse::fromDomain)
                .orElseThrow(() -> new ChatAiRunMetricNotFoundApplicationException(id));
    }
}
