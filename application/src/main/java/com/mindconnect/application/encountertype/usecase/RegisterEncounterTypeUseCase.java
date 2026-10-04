package com.mindconnect.application.encountertype.usecase;

import com.mindconnect.application.encountertype.command.RegisterEncounterTypeCommand;
import com.mindconnect.application.encountertype.dto.EncounterTypeResponse;
import com.mindconnect.domain.encountertype.model.aggregate.EncounterType;
import com.mindconnect.domain.encountertype.port.repository.EncounterTypeRepository;

public class RegisterEncounterTypeUseCase {

    private final EncounterTypeRepository repository;

    public RegisterEncounterTypeUseCase(EncounterTypeRepository repository) {
        this.repository = repository;
    }

    public EncounterTypeResponse execute(RegisterEncounterTypeCommand command) {
        EncounterType aggregate = EncounterType.register(
                command.code(), command.name());
        EncounterType saved = repository.save(aggregate);
        return EncounterTypeResponse.fromDomain(saved);
    }
}
