package com.mindconnect.application.chatairun.usecase;

import java.time.LocalDateTime;

import com.mindconnect.application.chatairun.exception.ChatAiRunNotFoundApplicationException;
import com.mindconnect.domain.chatairun.event.ChatAiRunDeletedEvent;
import com.mindconnect.domain.chatairun.model.valueobject.ChatAiRunId;
import com.mindconnect.domain.chatairun.port.repository.ChatAiRunRepository;

public class DeleteChatAiRunUseCase {

    private final ChatAiRunRepository repository;

    public DeleteChatAiRunUseCase(ChatAiRunRepository repository) {
        this.repository = repository;
    }

    public ChatAiRunDeletedEvent execute(ChatAiRunId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new ChatAiRunNotFoundApplicationException(id));

        repository.delete(aggregate);

        return new ChatAiRunDeletedEvent(id, LocalDateTime.now());
    }
}
