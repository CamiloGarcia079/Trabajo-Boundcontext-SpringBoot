package com.mindconnect.application.conversationstatus.usecase;

import java.time.LocalDateTime;

import com.mindconnect.application.conversationstatus.exception.ConversationStatusNotFoundApplicationException;
import com.mindconnect.domain.conversationstatus.event.ConversationStatusDeletedEvent;
import com.mindconnect.domain.conversationstatus.model.valueobject.ConversationStatusId;
import com.mindconnect.domain.conversationstatus.port.repository.ConversationStatusRepository;

public class DeleteConversationStatusUseCase {

    private final ConversationStatusRepository repository;

    public DeleteConversationStatusUseCase(ConversationStatusRepository repository) {
        this.repository = repository;
    }

    public ConversationStatusDeletedEvent execute(ConversationStatusId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new ConversationStatusNotFoundApplicationException(id));

        repository.delete(aggregate);

        return new ConversationStatusDeletedEvent(id, LocalDateTime.now());
    }
}
