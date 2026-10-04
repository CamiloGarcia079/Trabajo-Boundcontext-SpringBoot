package com.mindconnect.application.stateregion.usecase;

import com.mindconnect.application.stateregion.dto.StateRegionResponse;
import com.mindconnect.application.stateregion.exception.StateRegionNotFoundApplicationException;
import com.mindconnect.domain.stateregion.model.valueobject.StateRegionId;
import com.mindconnect.domain.stateregion.port.repository.StateRegionRepository;

public class GetStateRegionByIdUseCase {

    private final StateRegionRepository repository;

    public GetStateRegionByIdUseCase(StateRegionRepository repository) {
        this.repository = repository;
    }

    public StateRegionResponse execute(StateRegionId id) {
        return repository.findById(id)
                .map(StateRegionResponse::fromDomain)
                .orElseThrow(() -> new StateRegionNotFoundApplicationException(id));
    }
}
