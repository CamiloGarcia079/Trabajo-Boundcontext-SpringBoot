package com.mindconnect.application.chatairun.usecase;

import com.mindconnect.application.chatairun.command.RegisterChatAiRunCommand;
import com.mindconnect.application.chatairun.dto.ChatAiRunResponse;
import com.mindconnect.domain.chatairun.model.aggregate.ChatAiRun;
import com.mindconnect.domain.chatairun.port.repository.ChatAiRunRepository;

public class RegisterChatAiRunUseCase {

    private final ChatAiRunRepository repository;

    public RegisterChatAiRunUseCase(ChatAiRunRepository repository) {
        this.repository = repository;
    }

    public ChatAiRunResponse execute(RegisterChatAiRunCommand command) {
        ChatAiRun aggregate = ChatAiRun.register(
                command.conversationId(), command.messageId(), command.modelId(), command.aiRunStatusId());
        ChatAiRun saved = repository.save(aggregate);
        return ChatAiRunResponse.fromDomain(saved);
    }
}
