package com.mindconnect.application.priority.usecase;

import com.mindconnect.application.priority.command.UpdatePriorityCommand;
import com.mindconnect.application.priority.dto.PriorityResponse;
import com.mindconnect.application.priority.exception.PriorityNotFoundApplicationException;
import com.mindconnect.domain.priority.model.aggregate.Priority;
import com.mindconnect.domain.priority.port.repository.PriorityRepository;

public class UpdatePriorityUseCase {

    private final PriorityRepository repository;

    public UpdatePriorityUseCase(PriorityRepository repository) {
        this.repository = repository;
    }

    public PriorityResponse execute(UpdatePriorityCommand command) {
        Priority aggregate = repository.findById(command.id())
                .orElseThrow(() -> new PriorityNotFoundApplicationException(command.id()));
        aggregate.update(
                command.namePriority());
        Priority saved = repository.save(aggregate);
        return PriorityResponse.fromDomain(saved);
    }
}
