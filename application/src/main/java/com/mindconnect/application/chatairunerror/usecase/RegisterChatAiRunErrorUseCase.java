package com.mindconnect.application.chatairunerror.usecase;

import com.mindconnect.application.chatairunerror.command.RegisterChatAiRunErrorCommand;
import com.mindconnect.application.chatairunerror.dto.ChatAiRunErrorResponse;
import com.mindconnect.domain.chatairunerror.model.aggregate.ChatAiRunError;
import com.mindconnect.domain.chatairunerror.port.repository.ChatAiRunErrorRepository;

public class RegisterChatAiRunErrorUseCase {

    private final ChatAiRunErrorRepository repository;

    public RegisterChatAiRunErrorUseCase(ChatAiRunErrorRepository repository) {
        this.repository = repository;
    }

    public ChatAiRunErrorResponse execute(RegisterChatAiRunErrorCommand command) {
        ChatAiRunError aggregate = ChatAiRunError.register(
                command.aiRunId(), command.errorMessage(), command.errorCode(), command.providerErrorId());
        ChatAiRunError saved = repository.save(aggregate);
        return ChatAiRunErrorResponse.fromDomain(saved);
    }
}
