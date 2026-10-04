package com.mindconnect.application.chatescalationassignment.usecase;

import java.util.List;

import com.mindconnect.application.chatescalationassignment.dto.ChatEscalationAssignmentResponse;
import com.mindconnect.domain.chatescalationassignment.port.repository.ChatEscalationAssignmentRepository;

public class ListChatEscalationAssignmentUseCase {

    private final ChatEscalationAssignmentRepository repository;

    public ListChatEscalationAssignmentUseCase(ChatEscalationAssignmentRepository repository) {
        this.repository = repository;
    }

    public List<ChatEscalationAssignmentResponse> execute() {
        return repository.findAll()
                .stream()
                .map(ChatEscalationAssignmentResponse::fromDomain)
                .toList();
    }
}
