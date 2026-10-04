package com.mindconnect.application.encounter.usecase;

import java.util.List;

import com.mindconnect.application.encounter.dto.EncounterResponse;
import com.mindconnect.domain.encounter.port.repository.EncounterRepository;

public class ListEncounterUseCase {

    private final EncounterRepository repository;

    public ListEncounterUseCase(EncounterRepository repository) {
        this.repository = repository;
    }

    public List<EncounterResponse> execute() {
        return repository.findAll()
                .stream()
                .map(EncounterResponse::fromDomain)
                .toList();
    }
}
