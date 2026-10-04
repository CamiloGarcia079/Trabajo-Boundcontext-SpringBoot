package com.mindconnect.application.messagetype.usecase;

import java.util.List;

import com.mindconnect.application.messagetype.dto.MessageTypeResponse;
import com.mindconnect.domain.messagetype.port.repository.MessageTypeRepository;

public class ListMessageTypeUseCase {

    private final MessageTypeRepository repository;

    public ListMessageTypeUseCase(MessageTypeRepository repository) {
        this.repository = repository;
    }

    public List<MessageTypeResponse> execute() {
        return repository.findAll()
                .stream()
                .map(MessageTypeResponse::fromDomain)
                .toList();
    }
}
