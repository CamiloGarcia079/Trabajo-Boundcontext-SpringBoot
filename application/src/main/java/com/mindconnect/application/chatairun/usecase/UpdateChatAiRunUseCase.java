package com.mindconnect.application.chatairun.usecase;

import com.mindconnect.application.chatairun.command.UpdateChatAiRunCommand;
import com.mindconnect.application.chatairun.dto.ChatAiRunResponse;
import com.mindconnect.application.chatairun.exception.ChatAiRunNotFoundApplicationException;
import com.mindconnect.domain.chatairun.model.aggregate.ChatAiRun;
import com.mindconnect.domain.chatairun.port.repository.ChatAiRunRepository;

public class UpdateChatAiRunUseCase {

    private final ChatAiRunRepository repository;

    public UpdateChatAiRunUseCase(ChatAiRunRepository repository) {
        this.repository = repository;
    }

    public ChatAiRunResponse execute(UpdateChatAiRunCommand command) {
        ChatAiRun aggregate = repository.findById(command.id())
                .orElseThrow(() -> new ChatAiRunNotFoundApplicationException(command.id()));
        aggregate.update(
                command.conversationId(), command.messageId(), command.modelId(), command.aiRunStatusId());
        ChatAiRun saved = repository.save(aggregate);
        return ChatAiRunResponse.fromDomain(saved);
    }
}
