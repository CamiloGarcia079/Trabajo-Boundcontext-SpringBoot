package com.mindconnect.application.encountermodality.usecase;

import java.time.LocalDateTime;

import com.mindconnect.application.encountermodality.exception.EncounterModalityNotFoundApplicationException;
import com.mindconnect.domain.encountermodality.event.EncounterModalityDeletedEvent;
import com.mindconnect.domain.encountermodality.model.valueobject.EncounterModalityId;
import com.mindconnect.domain.encountermodality.port.repository.EncounterModalityRepository;

public class DeleteEncounterModalityUseCase {

    private final EncounterModalityRepository repository;

    public DeleteEncounterModalityUseCase(EncounterModalityRepository repository) {
        this.repository = repository;
    }

    public EncounterModalityDeletedEvent execute(EncounterModalityId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new EncounterModalityNotFoundApplicationException(id));

        repository.delete(aggregate);

        return new EncounterModalityDeletedEvent(id, LocalDateTime.now());
    }
}
