package com.mindconnect.application.chatairunmetric.usecase;

import com.mindconnect.application.chatairunmetric.command.UpdateChatAiRunMetricCommand;
import com.mindconnect.application.chatairunmetric.dto.ChatAiRunMetricResponse;
import com.mindconnect.application.chatairunmetric.exception.ChatAiRunMetricNotFoundApplicationException;
import com.mindconnect.domain.chatairunmetric.model.aggregate.ChatAiRunMetric;
import com.mindconnect.domain.chatairunmetric.port.repository.ChatAiRunMetricRepository;

public class UpdateChatAiRunMetricUseCase {

    private final ChatAiRunMetricRepository repository;

    public UpdateChatAiRunMetricUseCase(ChatAiRunMetricRepository repository) {
        this.repository = repository;
    }

    public ChatAiRunMetricResponse execute(UpdateChatAiRunMetricCommand command) {
        ChatAiRunMetric aggregate = repository.findById(command.id())
                .orElseThrow(() -> new ChatAiRunMetricNotFoundApplicationException(command.id()));
        aggregate.update(
                command.aiRunId(), command.promptTokens(), command.completionTokens(), command.totalTokens(), command.cost());
        ChatAiRunMetric saved = repository.save(aggregate);
        return ChatAiRunMetricResponse.fromDomain(saved);
    }
}
