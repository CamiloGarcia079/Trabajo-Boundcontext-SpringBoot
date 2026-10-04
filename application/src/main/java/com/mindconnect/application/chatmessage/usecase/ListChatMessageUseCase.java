package com.mindconnect.application.chatmessage.usecase;

import java.util.List;

import com.mindconnect.application.chatmessage.dto.ChatMessageResponse;
import com.mindconnect.domain.chatmessage.port.repository.ChatMessageRepository;

public class ListChatMessageUseCase {

    private final ChatMessageRepository repository;

    public ListChatMessageUseCase(ChatMessageRepository repository) {
        this.repository = repository;
    }

    public List<ChatMessageResponse> execute() {
        return repository.findAll()
                .stream()
                .map(ChatMessageResponse::fromDomain)
                .toList();
    }
}
