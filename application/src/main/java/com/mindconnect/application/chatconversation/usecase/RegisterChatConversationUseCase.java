package com.mindconnect.application.chatconversation.usecase;

import com.mindconnect.application.chatconversation.command.RegisterChatConversationCommand;
import com.mindconnect.application.chatconversation.dto.ChatConversationResponse;
import com.mindconnect.domain.chatconversation.model.aggregate.ChatConversation;
import com.mindconnect.domain.chatconversation.port.repository.ChatConversationRepository;

public class RegisterChatConversationUseCase {

    private final ChatConversationRepository repository;

    public RegisterChatConversationUseCase(ChatConversationRepository repository) {
        this.repository = repository;
    }

    public ChatConversationResponse execute(RegisterChatConversationCommand command) {
        ChatConversation aggregate = ChatConversation.register(
                command.conversationStatusId(), command.priorityId(), command.lastMessageAt(), command.closed(), command.closedAt(), command.closedBy());
        ChatConversation saved = repository.save(aggregate);
        return ChatConversationResponse.fromDomain(saved);
    }
}
