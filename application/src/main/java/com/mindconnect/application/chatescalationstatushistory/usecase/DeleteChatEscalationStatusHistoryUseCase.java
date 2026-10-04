package com.mindconnect.application.chatescalationstatushistory.usecase;

import java.time.LocalDateTime;

import com.mindconnect.application.chatescalationstatushistory.exception.ChatEscalationStatusHistoryNotFoundApplicationException;
import com.mindconnect.domain.chatescalationstatushistory.event.ChatEscalationStatusHistoryDeletedEvent;
import com.mindconnect.domain.chatescalationstatushistory.model.valueobject.ChatEscalationStatusHistoryId;
import com.mindconnect.domain.chatescalationstatushistory.port.repository.ChatEscalationStatusHistoryRepository;

public class DeleteChatEscalationStatusHistoryUseCase {

    private final ChatEscalationStatusHistoryRepository repository;

    public DeleteChatEscalationStatusHistoryUseCase(ChatEscalationStatusHistoryRepository repository) {
        this.repository = repository;
    }

    public ChatEscalationStatusHistoryDeletedEvent execute(ChatEscalationStatusHistoryId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new ChatEscalationStatusHistoryNotFoundApplicationException(id));

        repository.delete(aggregate);

        return new ChatEscalationStatusHistoryDeletedEvent(id, LocalDateTime.now());
    }
}
