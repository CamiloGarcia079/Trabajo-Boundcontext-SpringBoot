package com.mindconnect.application.chatescalationassignment.usecase;

import java.time.LocalDateTime;

import com.mindconnect.application.chatescalationassignment.exception.ChatEscalationAssignmentNotFoundApplicationException;
import com.mindconnect.domain.chatescalationassignment.event.ChatEscalationAssignmentDeletedEvent;
import com.mindconnect.domain.chatescalationassignment.model.valueobject.ChatEscalationAssignmentId;
import com.mindconnect.domain.chatescalationassignment.port.repository.ChatEscalationAssignmentRepository;

public class DeleteChatEscalationAssignmentUseCase {

    private final ChatEscalationAssignmentRepository repository;

    public DeleteChatEscalationAssignmentUseCase(ChatEscalationAssignmentRepository repository) {
        this.repository = repository;
    }

    public ChatEscalationAssignmentDeletedEvent execute(ChatEscalationAssignmentId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new ChatEscalationAssignmentNotFoundApplicationException(id));

        repository.delete(aggregate);

        return new ChatEscalationAssignmentDeletedEvent(id, LocalDateTime.now());
    }
}
