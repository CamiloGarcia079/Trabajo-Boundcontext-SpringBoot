package com.mindconnect.application.chatescalation.usecase;

import java.util.List;

import com.mindconnect.application.chatescalation.dto.ChatEscalationResponse;
import com.mindconnect.domain.chatescalation.port.repository.ChatEscalationRepository;

public class ListChatEscalationUseCase {

    private final ChatEscalationRepository repository;

    public ListChatEscalationUseCase(ChatEscalationRepository repository) {
        this.repository = repository;
    }

    public List<ChatEscalationResponse> execute() {
        return repository.findAll()
                .stream()
                .map(ChatEscalationResponse::fromDomain)
                .toList();
    }
}
