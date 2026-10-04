package com.mindconnect.application.encountertype.usecase;

import com.mindconnect.application.encountertype.command.UpdateEncounterTypeCommand;
import com.mindconnect.application.encountertype.dto.EncounterTypeResponse;
import com.mindconnect.application.encountertype.exception.EncounterTypeNotFoundApplicationException;
import com.mindconnect.domain.encountertype.model.aggregate.EncounterType;
import com.mindconnect.domain.encountertype.port.repository.EncounterTypeRepository;

public class UpdateEncounterTypeUseCase {

    private final EncounterTypeRepository repository;

    public UpdateEncounterTypeUseCase(EncounterTypeRepository repository) {
        this.repository = repository;
    }

    public EncounterTypeResponse execute(UpdateEncounterTypeCommand command) {
        EncounterType aggregate = repository.findById(command.id())
                .orElseThrow(() -> new EncounterTypeNotFoundApplicationException(command.id()));
        aggregate.update(
                command.code(), command.name());
        EncounterType saved = repository.save(aggregate);
        return EncounterTypeResponse.fromDomain(saved);
    }
}
