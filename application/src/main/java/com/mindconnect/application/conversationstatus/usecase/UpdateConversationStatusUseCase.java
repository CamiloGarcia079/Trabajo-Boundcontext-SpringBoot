package com.mindconnect.application.conversationstatus.usecase;

import com.mindconnect.application.conversationstatus.command.UpdateConversationStatusCommand;
import com.mindconnect.application.conversationstatus.dto.ConversationStatusResponse;
import com.mindconnect.application.conversationstatus.exception.ConversationStatusNotFoundApplicationException;
import com.mindconnect.domain.conversationstatus.model.aggregate.ConversationStatus;
import com.mindconnect.domain.conversationstatus.port.repository.ConversationStatusRepository;

public class UpdateConversationStatusUseCase {

    private final ConversationStatusRepository repository;

    public UpdateConversationStatusUseCase(ConversationStatusRepository repository) {
        this.repository = repository;
    }

    public ConversationStatusResponse execute(UpdateConversationStatusCommand command) {
        ConversationStatus aggregate = repository.findById(command.id())
                .orElseThrow(() -> new ConversationStatusNotFoundApplicationException(command.id()));
        aggregate.update(
                command.nameStatus());
        ConversationStatus saved = repository.save(aggregate);
        return ConversationStatusResponse.fromDomain(saved);
    }
}
