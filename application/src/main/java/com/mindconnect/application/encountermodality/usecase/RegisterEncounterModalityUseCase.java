package com.mindconnect.application.encountermodality.usecase;

import com.mindconnect.application.encountermodality.command.RegisterEncounterModalityCommand;
import com.mindconnect.application.encountermodality.dto.EncounterModalityResponse;
import com.mindconnect.domain.encountermodality.model.aggregate.EncounterModality;
import com.mindconnect.domain.encountermodality.port.repository.EncounterModalityRepository;

public class RegisterEncounterModalityUseCase {

    private final EncounterModalityRepository repository;

    public RegisterEncounterModalityUseCase(EncounterModalityRepository repository) {
        this.repository = repository;
    }

    public EncounterModalityResponse execute(RegisterEncounterModalityCommand command) {
        EncounterModality aggregate = EncounterModality.register(
                command.code(), command.name());
        EncounterModality saved = repository.save(aggregate);
        return EncounterModalityResponse.fromDomain(saved);
    }
}
