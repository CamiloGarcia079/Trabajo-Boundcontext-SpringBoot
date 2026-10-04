package com.mindconnect.application.chatescalationstatushistory.usecase;

import java.util.List;

import com.mindconnect.application.chatescalationstatushistory.dto.ChatEscalationStatusHistoryResponse;
import com.mindconnect.domain.chatescalationstatushistory.port.repository.ChatEscalationStatusHistoryRepository;

public class ListChatEscalationStatusHistoryUseCase {

    private final ChatEscalationStatusHistoryRepository repository;

    public ListChatEscalationStatusHistoryUseCase(ChatEscalationStatusHistoryRepository repository) {
        this.repository = repository;
    }

    public List<ChatEscalationStatusHistoryResponse> execute() {
        return repository.findAll()
                .stream()
                .map(ChatEscalationStatusHistoryResponse::fromDomain)
                .toList();
    }
}
