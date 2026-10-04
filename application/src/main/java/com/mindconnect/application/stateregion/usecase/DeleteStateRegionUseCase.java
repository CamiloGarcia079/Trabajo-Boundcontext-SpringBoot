package com.mindconnect.application.stateregion.usecase;

import java.time.LocalDateTime;

import com.mindconnect.application.stateregion.exception.StateRegionNotFoundApplicationException;
import com.mindconnect.domain.stateregion.event.StateRegionDeletedEvent;
import com.mindconnect.domain.stateregion.model.valueobject.StateRegionId;
import com.mindconnect.domain.stateregion.port.repository.StateRegionRepository;

public class DeleteStateRegionUseCase {

    private final StateRegionRepository repository;

    public DeleteStateRegionUseCase(StateRegionRepository repository) {
        this.repository = repository;
    }

    public StateRegionDeletedEvent execute(StateRegionId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new StateRegionNotFoundApplicationException(id));

        repository.delete(aggregate);

        return new StateRegionDeletedEvent(id, LocalDateTime.now());
    }
}
