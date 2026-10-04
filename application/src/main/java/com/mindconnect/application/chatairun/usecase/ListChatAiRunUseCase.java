package com.mindconnect.application.chatairun.usecase;

import java.util.List;

import com.mindconnect.application.chatairun.dto.ChatAiRunResponse;
import com.mindconnect.domain.chatairun.port.repository.ChatAiRunRepository;

public class ListChatAiRunUseCase {

    private final ChatAiRunRepository repository;

    public ListChatAiRunUseCase(ChatAiRunRepository repository) {
        this.repository = repository;
    }

    public List<ChatAiRunResponse> execute() {
        return repository.findAll()
                .stream()
                .map(ChatAiRunResponse::fromDomain)
                .toList();
    }
}
