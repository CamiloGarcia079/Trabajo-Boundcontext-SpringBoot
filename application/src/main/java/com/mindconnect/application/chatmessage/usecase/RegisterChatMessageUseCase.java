package com.mindconnect.application.chatmessage.usecase;

import com.mindconnect.application.chatmessage.command.RegisterChatMessageCommand;
import com.mindconnect.application.chatmessage.dto.ChatMessageResponse;
import com.mindconnect.domain.chatmessage.model.aggregate.ChatMessage;
import com.mindconnect.domain.chatmessage.port.repository.ChatMessageRepository;

public class RegisterChatMessageUseCase {

    private final ChatMessageRepository repository;

    public RegisterChatMessageUseCase(ChatMessageRepository repository) {
        this.repository = repository;
    }

    public ChatMessageResponse execute(RegisterChatMessageCommand command) {
        ChatMessage aggregate = ChatMessage.register(
                command.conversationId(), command.messageTypeId(), command.participantId(), command.content(), command.metadata());
        ChatMessage saved = repository.save(aggregate);
        return ChatMessageResponse.fromDomain(saved);
    }
}
