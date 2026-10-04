package com.mindconnect.application.chatairun.usecase;

import com.mindconnect.application.chatairun.dto.ChatAiRunResponse;
import com.mindconnect.application.chatairun.exception.ChatAiRunNotFoundApplicationException;
import com.mindconnect.domain.chatairun.model.valueobject.ChatAiRunId;
import com.mindconnect.domain.chatairun.port.repository.ChatAiRunRepository;

public class GetChatAiRunByIdUseCase {

    private final ChatAiRunRepository repository;

    public GetChatAiRunByIdUseCase(ChatAiRunRepository repository) {
        this.repository = repository;
    }

    public ChatAiRunResponse execute(ChatAiRunId id) {
        return repository.findById(id)
                .map(ChatAiRunResponse::fromDomain)
                .orElseThrow(() -> new ChatAiRunNotFoundApplicationException(id));
    }
}
