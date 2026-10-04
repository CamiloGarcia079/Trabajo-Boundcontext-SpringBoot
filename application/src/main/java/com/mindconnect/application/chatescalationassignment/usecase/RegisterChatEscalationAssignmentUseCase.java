package com.mindconnect.application.chatescalationassignment.usecase;

import com.mindconnect.application.chatescalationassignment.command.RegisterChatEscalationAssignmentCommand;
import com.mindconnect.application.chatescalationassignment.dto.ChatEscalationAssignmentResponse;
import com.mindconnect.domain.chatescalationassignment.model.aggregate.ChatEscalationAssignment;
import com.mindconnect.domain.chatescalationassignment.port.repository.ChatEscalationAssignmentRepository;

public class RegisterChatEscalationAssignmentUseCase {

    private final ChatEscalationAssignmentRepository repository;

    public RegisterChatEscalationAssignmentUseCase(ChatEscalationAssignmentRepository repository) {
        this.repository = repository;
    }

    public ChatEscalationAssignmentResponse execute(RegisterChatEscalationAssignmentCommand command) {
        ChatEscalationAssignment aggregate = ChatEscalationAssignment.register(
                command.escalationId(), command.professionalId(), command.assignedAt());
        ChatEscalationAssignment saved = repository.save(aggregate);
        return ChatEscalationAssignmentResponse.fromDomain(saved);
    }
}
