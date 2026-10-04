package com.mindconnect.application.chatescalationstatushistory.usecase;

import com.mindconnect.application.chatescalationstatushistory.dto.ChatEscalationStatusHistoryResponse;
import com.mindconnect.application.chatescalationstatushistory.exception.ChatEscalationStatusHistoryNotFoundApplicationException;
import com.mindconnect.domain.chatescalationstatushistory.model.valueobject.ChatEscalationStatusHistoryId;
import com.mindconnect.domain.chatescalationstatushistory.port.repository.ChatEscalationStatusHistoryRepository;

public class GetChatEscalationStatusHistoryByIdUseCase {

    private final ChatEscalationStatusHistoryRepository repository;

    public GetChatEscalationStatusHistoryByIdUseCase(ChatEscalationStatusHistoryRepository repository) {
        this.repository = repository;
    }

    public ChatEscalationStatusHistoryResponse execute(ChatEscalationStatusHistoryId id) {
        return repository.findById(id)
                .map(ChatEscalationStatusHistoryResponse::fromDomain)
                .orElseThrow(() -> new ChatEscalationStatusHistoryNotFoundApplicationException(id));
    }
}
