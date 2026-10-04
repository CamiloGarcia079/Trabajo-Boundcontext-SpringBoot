package com.mindconnect.application.stateregion.usecase;

import java.util.List;

import com.mindconnect.application.stateregion.dto.StateRegionResponse;
import com.mindconnect.domain.stateregion.port.repository.StateRegionRepository;

public class ListStateRegionUseCase {

    private final StateRegionRepository repository;

    public ListStateRegionUseCase(StateRegionRepository repository) {
        this.repository = repository;
    }

    public List<StateRegionResponse> execute() {
        return repository.findAll()
                .stream()
                .map(StateRegionResponse::fromDomain)
                .toList();
    }
}
