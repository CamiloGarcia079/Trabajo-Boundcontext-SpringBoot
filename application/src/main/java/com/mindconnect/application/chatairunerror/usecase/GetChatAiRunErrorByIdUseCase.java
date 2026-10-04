package com.mindconnect.application.chatairunerror.usecase;

import com.mindconnect.application.chatairunerror.dto.ChatAiRunErrorResponse;
import com.mindconnect.application.chatairunerror.exception.ChatAiRunErrorNotFoundApplicationException;
import com.mindconnect.domain.chatairunerror.model.valueobject.ChatAiRunErrorId;
import com.mindconnect.domain.chatairunerror.port.repository.ChatAiRunErrorRepository;

public class GetChatAiRunErrorByIdUseCase {

    private final ChatAiRunErrorRepository repository;

    public GetChatAiRunErrorByIdUseCase(ChatAiRunErrorRepository repository) {
        this.repository = repository;
    }

    public ChatAiRunErrorResponse execute(ChatAiRunErrorId id) {
        return repository.findById(id)
                .map(ChatAiRunErrorResponse::fromDomain)
                .orElseThrow(() -> new ChatAiRunErrorNotFoundApplicationException(id));
    }
}
