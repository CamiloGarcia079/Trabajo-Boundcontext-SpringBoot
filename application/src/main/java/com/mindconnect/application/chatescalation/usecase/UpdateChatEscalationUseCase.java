package com.mindconnect.application.chatescalation.usecase;

import com.mindconnect.application.chatescalation.command.UpdateChatEscalationCommand;
import com.mindconnect.application.chatescalation.dto.ChatEscalationResponse;
import com.mindconnect.application.chatescalation.exception.ChatEscalationNotFoundApplicationException;
import com.mindconnect.domain.chatescalation.model.aggregate.ChatEscalation;
import com.mindconnect.domain.chatescalation.port.repository.ChatEscalationRepository;

public class UpdateChatEscalationUseCase {

    private final ChatEscalationRepository repository;

    public UpdateChatEscalationUseCase(ChatEscalationRepository repository) {
        this.repository = repository;
    }

    public ChatEscalationResponse execute(UpdateChatEscalationCommand command) {
        ChatEscalation aggregate = repository.findById(command.id())
                .orElseThrow(() -> new ChatEscalationNotFoundApplicationException(command.id()));
        aggregate.update(
                command.conversationId(), command.statusId(), command.fromAi(), command.reason());
        ChatEscalation saved = repository.save(aggregate);
        return ChatEscalationResponse.fromDomain(saved);
    }
}
