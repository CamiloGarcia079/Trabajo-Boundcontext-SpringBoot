package com.mindconnect.application.chatairunerror.usecase;

import com.mindconnect.application.chatairunerror.command.UpdateChatAiRunErrorCommand;
import com.mindconnect.application.chatairunerror.dto.ChatAiRunErrorResponse;
import com.mindconnect.application.chatairunerror.exception.ChatAiRunErrorNotFoundApplicationException;
import com.mindconnect.domain.chatairunerror.model.aggregate.ChatAiRunError;
import com.mindconnect.domain.chatairunerror.port.repository.ChatAiRunErrorRepository;

public class UpdateChatAiRunErrorUseCase {

    private final ChatAiRunErrorRepository repository;

    public UpdateChatAiRunErrorUseCase(ChatAiRunErrorRepository repository) {
        this.repository = repository;
    }

    public ChatAiRunErrorResponse execute(UpdateChatAiRunErrorCommand command) {
        ChatAiRunError aggregate = repository.findById(command.id())
                .orElseThrow(() -> new ChatAiRunErrorNotFoundApplicationException(command.id()));
        aggregate.update(
                command.aiRunId(), command.errorMessage(), command.errorCode(), command.providerErrorId());
        ChatAiRunError saved = repository.save(aggregate);
        return ChatAiRunErrorResponse.fromDomain(saved);
    }
}
