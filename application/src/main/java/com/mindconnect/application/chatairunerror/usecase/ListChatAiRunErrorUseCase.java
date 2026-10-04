package com.mindconnect.application.chatairunerror.usecase;

import java.util.List;

import com.mindconnect.application.chatairunerror.dto.ChatAiRunErrorResponse;
import com.mindconnect.domain.chatairunerror.port.repository.ChatAiRunErrorRepository;

public class ListChatAiRunErrorUseCase {

    private final ChatAiRunErrorRepository repository;

    public ListChatAiRunErrorUseCase(ChatAiRunErrorRepository repository) {
        this.repository = repository;
    }

    public List<ChatAiRunErrorResponse> execute() {
        return repository.findAll()
                .stream()
                .map(ChatAiRunErrorResponse::fromDomain)
                .toList();
    }
}
