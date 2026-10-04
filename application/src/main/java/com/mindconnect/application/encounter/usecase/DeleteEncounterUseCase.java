package com.mindconnect.application.encounter.usecase;

import java.time.LocalDateTime;

import com.mindconnect.application.encounter.exception.EncounterNotFoundApplicationException;
import com.mindconnect.domain.encounter.event.EncounterDeletedEvent;
import com.mindconnect.domain.encounter.model.valueobject.EncounterId;
import com.mindconnect.domain.encounter.port.repository.EncounterRepository;

public class DeleteEncounterUseCase {

    private final EncounterRepository repository;

    public DeleteEncounterUseCase(EncounterRepository repository) {
        this.repository = repository;
    }

    public EncounterDeletedEvent execute(EncounterId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new EncounterNotFoundApplicationException(id));

        repository.delete(aggregate);

        return new EncounterDeletedEvent(id, LocalDateTime.now());
    }
}
