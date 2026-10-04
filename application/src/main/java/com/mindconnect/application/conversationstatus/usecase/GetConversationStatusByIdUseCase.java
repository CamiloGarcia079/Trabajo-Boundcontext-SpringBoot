package com.mindconnect.application.conversationstatus.usecase;

import com.mindconnect.application.conversationstatus.dto.ConversationStatusResponse;
import com.mindconnect.application.conversationstatus.exception.ConversationStatusNotFoundApplicationException;
import com.mindconnect.domain.conversationstatus.model.valueobject.ConversationStatusId;
import com.mindconnect.domain.conversationstatus.port.repository.ConversationStatusRepository;

public class GetConversationStatusByIdUseCase {

    private final ConversationStatusRepository repository;

    public GetConversationStatusByIdUseCase(ConversationStatusRepository repository) {
        this.repository = repository;
    }

    public ConversationStatusResponse execute(ConversationStatusId id) {
        return repository.findById(id)
                .map(ConversationStatusResponse::fromDomain)
                .orElseThrow(() -> new ConversationStatusNotFoundApplicationException(id));
    }
}
