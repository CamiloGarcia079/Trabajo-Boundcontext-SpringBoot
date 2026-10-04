package com.mindconnect.application.conversationstatus.usecase;

import com.mindconnect.application.conversationstatus.command.RegisterConversationStatusCommand;
import com.mindconnect.application.conversationstatus.dto.ConversationStatusResponse;
import com.mindconnect.domain.conversationstatus.model.aggregate.ConversationStatus;
import com.mindconnect.domain.conversationstatus.port.repository.ConversationStatusRepository;

public class RegisterConversationStatusUseCase {

    private final ConversationStatusRepository repository;

    public RegisterConversationStatusUseCase(ConversationStatusRepository repository) {
        this.repository = repository;
    }

    public ConversationStatusResponse execute(RegisterConversationStatusCommand command) {
        ConversationStatus aggregate = ConversationStatus.register(
                command.nameStatus());
        ConversationStatus saved = repository.save(aggregate);
        return ConversationStatusResponse.fromDomain(saved);
    }
}
