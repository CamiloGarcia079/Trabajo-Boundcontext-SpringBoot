package com.mindconnect.application.chatconversation.usecase;

import com.mindconnect.application.chatconversation.dto.ChatConversationResponse;
import com.mindconnect.application.chatconversation.exception.ChatConversationNotFoundApplicationException;
import com.mindconnect.domain.chatconversation.model.valueobject.ChatConversationId;
import com.mindconnect.domain.chatconversation.port.repository.ChatConversationRepository;

public class GetChatConversationByIdUseCase {

    private final ChatConversationRepository repository;

    public GetChatConversationByIdUseCase(ChatConversationRepository repository) {
        this.repository = repository;
    }

    public ChatConversationResponse execute(ChatConversationId id) {
        return repository.findById(id)
                .map(ChatConversationResponse::fromDomain)
                .orElseThrow(() -> new ChatConversationNotFoundApplicationException(id));
    }
}
