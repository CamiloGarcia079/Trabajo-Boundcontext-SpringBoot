package com.mindconnect.application.chatescalation.usecase;

import java.time.LocalDateTime;

import com.mindconnect.application.chatescalation.exception.ChatEscalationNotFoundApplicationException;
import com.mindconnect.domain.chatescalation.event.ChatEscalationDeletedEvent;
import com.mindconnect.domain.chatescalation.model.valueobject.ChatEscalationId;
import com.mindconnect.domain.chatescalation.port.repository.ChatEscalationRepository;

public class DeleteChatEscalationUseCase {

    private final ChatEscalationRepository repository;

    public DeleteChatEscalationUseCase(ChatEscalationRepository repository) {
        this.repository = repository;
    }

    public ChatEscalationDeletedEvent execute(ChatEscalationId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new ChatEscalationNotFoundApplicationException(id));

        repository.delete(aggregate);

        return new ChatEscalationDeletedEvent(id, LocalDateTime.now());
    }
}
