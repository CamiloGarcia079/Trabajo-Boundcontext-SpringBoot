package com.mindconnect.application.chatescalationassignment.usecase;

import com.mindconnect.application.chatescalationassignment.command.UpdateChatEscalationAssignmentCommand;
import com.mindconnect.application.chatescalationassignment.dto.ChatEscalationAssignmentResponse;
import com.mindconnect.application.chatescalationassignment.exception.ChatEscalationAssignmentNotFoundApplicationException;
import com.mindconnect.domain.chatescalationassignment.model.aggregate.ChatEscalationAssignment;
import com.mindconnect.domain.chatescalationassignment.port.repository.ChatEscalationAssignmentRepository;

public class UpdateChatEscalationAssignmentUseCase {

    private final ChatEscalationAssignmentRepository repository;

    public UpdateChatEscalationAssignmentUseCase(ChatEscalationAssignmentRepository repository) {
        this.repository = repository;
    }

    public ChatEscalationAssignmentResponse execute(UpdateChatEscalationAssignmentCommand command) {
        ChatEscalationAssignment aggregate = repository.findById(command.id())
                .orElseThrow(() -> new ChatEscalationAssignmentNotFoundApplicationException(command.id()));
        aggregate.update(
                command.escalationId(), command.professionalId(), command.assignedAt());
        ChatEscalationAssignment saved = repository.save(aggregate);
        return ChatEscalationAssignmentResponse.fromDomain(saved);
    }
}
