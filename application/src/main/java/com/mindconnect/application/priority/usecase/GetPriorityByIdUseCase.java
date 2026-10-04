package com.mindconnect.application.priority.usecase;

import com.mindconnect.application.priority.dto.PriorityResponse;
import com.mindconnect.application.priority.exception.PriorityNotFoundApplicationException;
import com.mindconnect.domain.priority.model.valueobject.PriorityId;
import com.mindconnect.domain.priority.port.repository.PriorityRepository;

public class GetPriorityByIdUseCase {

    private final PriorityRepository repository;

    public GetPriorityByIdUseCase(PriorityRepository repository) {
        this.repository = repository;
    }

    public PriorityResponse execute(PriorityId id) {
        return repository.findById(id)
                .map(PriorityResponse::fromDomain)
                .orElseThrow(() -> new PriorityNotFoundApplicationException(id));
    }
}
