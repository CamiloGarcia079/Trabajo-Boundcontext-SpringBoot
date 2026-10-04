package com.mindconnect.application.chatescalationstatushistory.usecase;

import com.mindconnect.application.chatescalationstatushistory.command.RegisterChatEscalationStatusHistoryCommand;
import com.mindconnect.application.chatescalationstatushistory.dto.ChatEscalationStatusHistoryResponse;
import com.mindconnect.domain.chatescalationstatushistory.model.aggregate.ChatEscalationStatusHistory;
import com.mindconnect.domain.chatescalationstatushistory.port.repository.ChatEscalationStatusHistoryRepository;

public class RegisterChatEscalationStatusHistoryUseCase {

    private final ChatEscalationStatusHistoryRepository repository;

    public RegisterChatEscalationStatusHistoryUseCase(ChatEscalationStatusHistoryRepository repository) {
        this.repository = repository;
    }

    public ChatEscalationStatusHistoryResponse execute(RegisterChatEscalationStatusHistoryCommand command) {
        ChatEscalationStatusHistory aggregate = ChatEscalationStatusHistory.register(
                command.escalationId(), command.escalationStatusId(), command.changedAt());
        ChatEscalationStatusHistory saved = repository.save(aggregate);
        return ChatEscalationStatusHistoryResponse.fromDomain(saved);
    }
}
