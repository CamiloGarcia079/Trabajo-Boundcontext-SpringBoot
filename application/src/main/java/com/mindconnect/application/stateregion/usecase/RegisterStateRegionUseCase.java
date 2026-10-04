package com.mindconnect.application.stateregion.usecase;

import com.mindconnect.application.stateregion.command.RegisterStateRegionCommand;
import com.mindconnect.application.stateregion.dto.StateRegionResponse;
import com.mindconnect.domain.stateregion.model.aggregate.StateRegion;
import com.mindconnect.domain.stateregion.port.repository.StateRegionRepository;

public class RegisterStateRegionUseCase {

    private final StateRegionRepository repository;

    public RegisterStateRegionUseCase(StateRegionRepository repository) {
        this.repository = repository;
    }

    public StateRegionResponse execute(RegisterStateRegionCommand command) {
        StateRegion aggregate = StateRegion.register(
                command.nameRegion(), command.codeRegion(), command.description(), command.countryId());
        StateRegion saved = repository.save(aggregate);
        return StateRegionResponse.fromDomain(saved);
    }
}
