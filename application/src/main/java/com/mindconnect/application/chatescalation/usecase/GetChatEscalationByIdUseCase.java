package com.mindconnect.application.chatescalation.usecase;

import com.mindconnect.application.chatescalation.dto.ChatEscalationResponse;
import com.mindconnect.application.chatescalation.exception.ChatEscalationNotFoundApplicationException;
import com.mindconnect.domain.chatescalation.model.valueobject.ChatEscalationId;
import com.mindconnect.domain.chatescalation.port.repository.ChatEscalationRepository;

public class GetChatEscalationByIdUseCase {

    private final ChatEscalationRepository repository;

    public GetChatEscalationByIdUseCase(ChatEscalationRepository repository) {
        this.repository = repository;
    }

    public ChatEscalationResponse execute(ChatEscalationId id) {
        return repository.findById(id)
                .map(ChatEscalationResponse::fromDomain)
                .orElseThrow(() -> new ChatEscalationNotFoundApplicationException(id));
    }
}
