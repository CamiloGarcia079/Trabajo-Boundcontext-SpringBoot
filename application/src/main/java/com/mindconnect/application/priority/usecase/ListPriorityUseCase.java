package com.mindconnect.application.priority.usecase;

import java.util.List;

import com.mindconnect.application.priority.dto.PriorityResponse;
import com.mindconnect.domain.priority.port.repository.PriorityRepository;

public class ListPriorityUseCase {

    private final PriorityRepository repository;

    public ListPriorityUseCase(PriorityRepository repository) {
        this.repository = repository;
    }

    public List<PriorityResponse> execute() {
        return repository.findAll()
                .stream()
                .map(PriorityResponse::fromDomain)
                .toList();
    }
}
