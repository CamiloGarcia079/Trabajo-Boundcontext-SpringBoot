package com.mindconnect.application.chatescalationassignment.usecase;

import com.mindconnect.application.chatescalationassignment.dto.ChatEscalationAssignmentResponse;
import com.mindconnect.application.chatescalationassignment.exception.ChatEscalationAssignmentNotFoundApplicationException;
import com.mindconnect.domain.chatescalationassignment.model.valueobject.ChatEscalationAssignmentId;
import com.mindconnect.domain.chatescalationassignment.port.repository.ChatEscalationAssignmentRepository;

public class GetChatEscalationAssignmentByIdUseCase {

    private final ChatEscalationAssignmentRepository repository;

    public GetChatEscalationAssignmentByIdUseCase(ChatEscalationAssignmentRepository repository) {
        this.repository = repository;
    }

    public ChatEscalationAssignmentResponse execute(ChatEscalationAssignmentId id) {
        return repository.findById(id)
                .map(ChatEscalationAssignmentResponse::fromDomain)
                .orElseThrow(() -> new ChatEscalationAssignmentNotFoundApplicationException(id));
    }
}
