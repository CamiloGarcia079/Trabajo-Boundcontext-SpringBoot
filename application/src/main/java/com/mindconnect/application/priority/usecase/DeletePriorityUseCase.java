package com.mindconnect.application.priority.usecase;

import java.time.LocalDateTime;

import com.mindconnect.application.priority.exception.PriorityNotFoundApplicationException;
import com.mindconnect.domain.priority.event.PriorityDeletedEvent;
import com.mindconnect.domain.priority.model.valueobject.PriorityId;
import com.mindconnect.domain.priority.port.repository.PriorityRepository;

public class DeletePriorityUseCase {

    private final PriorityRepository repository;

    public DeletePriorityUseCase(PriorityRepository repository) {
        this.repository = repository;
    }

    public PriorityDeletedEvent execute(PriorityId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new PriorityNotFoundApplicationException(id));

        repository.delete(aggregate);

        return new PriorityDeletedEvent(id, LocalDateTime.now());
    }
}
