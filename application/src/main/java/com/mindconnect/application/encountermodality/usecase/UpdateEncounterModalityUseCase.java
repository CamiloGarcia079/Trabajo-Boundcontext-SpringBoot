package com.mindconnect.application.encountermodality.usecase;

import com.mindconnect.application.encountermodality.command.UpdateEncounterModalityCommand;
import com.mindconnect.application.encountermodality.dto.EncounterModalityResponse;
import com.mindconnect.application.encountermodality.exception.EncounterModalityNotFoundApplicationException;
import com.mindconnect.domain.encountermodality.model.aggregate.EncounterModality;
import com.mindconnect.domain.encountermodality.port.repository.EncounterModalityRepository;

public class UpdateEncounterModalityUseCase {

    private final EncounterModalityRepository repository;

    public UpdateEncounterModalityUseCase(EncounterModalityRepository repository) {
        this.repository = repository;
    }

    public EncounterModalityResponse execute(UpdateEncounterModalityCommand command) {
        EncounterModality aggregate = repository.findById(command.id())
                .orElseThrow(() -> new EncounterModalityNotFoundApplicationException(command.id()));
        aggregate.update(
                command.code(), command.name());
        EncounterModality saved = repository.save(aggregate);
        return EncounterModalityResponse.fromDomain(saved);
    }
}
