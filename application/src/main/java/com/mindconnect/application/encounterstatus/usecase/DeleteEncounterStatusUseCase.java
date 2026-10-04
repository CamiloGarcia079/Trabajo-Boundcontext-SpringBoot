package com.mindconnect.application.encounterstatus.usecase;

import java.time.LocalDateTime;

import com.mindconnect.application.encounterstatus.exception.EncounterStatusNotFoundApplicationException;
import com.mindconnect.domain.encounterstatus.event.EncounterStatusDeletedEvent;
import com.mindconnect.domain.encounterstatus.model.valueobject.EncounterStatusId;
import com.mindconnect.domain.encounterstatus.port.repository.EncounterStatusRepository;

public class DeleteEncounterStatusUseCase {

    private final EncounterStatusRepository repository;

    public DeleteEncounterStatusUseCase(EncounterStatusRepository repository) {
        this.repository = repository;
    }

    public EncounterStatusDeletedEvent execute(EncounterStatusId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new EncounterStatusNotFoundApplicationException(id));

        repository.delete(aggregate);

        return new EncounterStatusDeletedEvent(id, LocalDateTime.now());
    }
}
