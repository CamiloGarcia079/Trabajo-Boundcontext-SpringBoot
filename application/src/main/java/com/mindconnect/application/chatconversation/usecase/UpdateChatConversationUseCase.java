package com.mindconnect.application.chatconversation.usecase;

import com.mindconnect.application.chatconversation.command.UpdateChatConversationCommand;
import com.mindconnect.application.chatconversation.dto.ChatConversationResponse;
import com.mindconnect.application.chatconversation.exception.ChatConversationNotFoundApplicationException;
import com.mindconnect.domain.chatconversation.model.aggregate.ChatConversation;
import com.mindconnect.domain.chatconversation.port.repository.ChatConversationRepository;

public class UpdateChatConversationUseCase {

    private final ChatConversationRepository repository;

    public UpdateChatConversationUseCase(ChatConversationRepository repository) {
        this.repository = repository;
    }

    public ChatConversationResponse execute(UpdateChatConversationCommand command) {
        ChatConversation aggregate = repository.findById(command.id())
                .orElseThrow(() -> new ChatConversationNotFoundApplicationException(command.id()));
        aggregate.update(
                command.conversationStatusId(), command.priorityId(), command.lastMessageAt(), command.closed(), command.closedAt(), command.closedBy());
        ChatConversation saved = repository.save(aggregate);
        return ChatConversationResponse.fromDomain(saved);
    }
}
