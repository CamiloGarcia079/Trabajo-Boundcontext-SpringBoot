package com.mindconnect.application.chatconversation.usecase;

import java.util.List;

import com.mindconnect.application.chatconversation.dto.ChatConversationResponse;
import com.mindconnect.domain.chatconversation.port.repository.ChatConversationRepository;

public class ListChatConversationUseCase {

    private final ChatConversationRepository repository;

    public ListChatConversationUseCase(ChatConversationRepository repository) {
        this.repository = repository;
    }

    public List<ChatConversationResponse> execute() {
        return repository.findAll()
                .stream()
                .map(ChatConversationResponse::fromDomain)
                .toList();
    }
}
