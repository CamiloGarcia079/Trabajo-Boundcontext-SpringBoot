package com.mindconnect.application.encountertype.usecase;

import java.time.LocalDateTime;

import com.mindconnect.application.encountertype.exception.EncounterTypeNotFoundApplicationException;
import com.mindconnect.domain.encountertype.event.EncounterTypeDeletedEvent;
import com.mindconnect.domain.encountertype.model.valueobject.EncounterTypeId;
import com.mindconnect.domain.encountertype.port.repository.EncounterTypeRepository;

public class DeleteEncounterTypeUseCase {

    private final EncounterTypeRepository repository;

    public DeleteEncounterTypeUseCase(EncounterTypeRepository repository) {
        this.repository = repository;
    }

    public EncounterTypeDeletedEvent execute(EncounterTypeId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new EncounterTypeNotFoundApplicationException(id));

        repository.delete(aggregate);

        return new EncounterTypeDeletedEvent(id, LocalDateTime.now());
    }
}
