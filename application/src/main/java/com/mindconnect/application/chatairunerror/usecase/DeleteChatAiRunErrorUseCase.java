package com.mindconnect.application.chatairunerror.usecase;

import java.time.LocalDateTime;

import com.mindconnect.application.chatairunerror.exception.ChatAiRunErrorNotFoundApplicationException;
import com.mindconnect.domain.chatairunerror.event.ChatAiRunErrorDeletedEvent;
import com.mindconnect.domain.chatairunerror.model.valueobject.ChatAiRunErrorId;
import com.mindconnect.domain.chatairunerror.port.repository.ChatAiRunErrorRepository;

public class DeleteChatAiRunErrorUseCase {

    private final ChatAiRunErrorRepository repository;

    public DeleteChatAiRunErrorUseCase(ChatAiRunErrorRepository repository) {
        this.repository = repository;
    }

    public ChatAiRunErrorDeletedEvent execute(ChatAiRunErrorId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new ChatAiRunErrorNotFoundApplicationException(id));

        repository.delete(aggregate);

        return new ChatAiRunErrorDeletedEvent(id, LocalDateTime.now());
    }
}
