package com.mindconnect.application.chatairunmetric.usecase;

import java.time.LocalDateTime;

import com.mindconnect.application.chatairunmetric.exception.ChatAiRunMetricNotFoundApplicationException;
import com.mindconnect.domain.chatairunmetric.event.ChatAiRunMetricDeletedEvent;
import com.mindconnect.domain.chatairunmetric.model.valueobject.ChatAiRunMetricId;
import com.mindconnect.domain.chatairunmetric.port.repository.ChatAiRunMetricRepository;

public class DeleteChatAiRunMetricUseCase {

    private final ChatAiRunMetricRepository repository;

    public DeleteChatAiRunMetricUseCase(ChatAiRunMetricRepository repository) {
        this.repository = repository;
    }

    public ChatAiRunMetricDeletedEvent execute(ChatAiRunMetricId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new ChatAiRunMetricNotFoundApplicationException(id));

        repository.delete(aggregate);

        return new ChatAiRunMetricDeletedEvent(id, LocalDateTime.now());
    }
}
