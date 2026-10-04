package com.mindconnect.application.stateregion.usecase;

import com.mindconnect.application.stateregion.command.UpdateStateRegionCommand;
import com.mindconnect.application.stateregion.dto.StateRegionResponse;
import com.mindconnect.application.stateregion.exception.StateRegionNotFoundApplicationException;
import com.mindconnect.domain.stateregion.model.aggregate.StateRegion;
import com.mindconnect.domain.stateregion.port.repository.StateRegionRepository;

public class UpdateStateRegionUseCase {

    private final StateRegionRepository repository;

    public UpdateStateRegionUseCase(StateRegionRepository repository) {
        this.repository = repository;
    }

    public StateRegionResponse execute(UpdateStateRegionCommand command) {
        StateRegion aggregate = repository.findById(command.id())
                .orElseThrow(() -> new StateRegionNotFoundApplicationException(command.id()));
        aggregate.update(
                command.nameRegion(), command.codeRegion(), command.description(), command.countryId());
        StateRegion saved = repository.save(aggregate);
        return StateRegionResponse.fromDomain(saved);
    }
}
