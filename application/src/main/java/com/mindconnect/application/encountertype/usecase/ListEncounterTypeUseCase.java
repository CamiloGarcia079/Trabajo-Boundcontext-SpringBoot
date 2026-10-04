package com.mindconnect.application.encountertype.usecase;

import java.util.List;

import com.mindconnect.application.encountertype.dto.EncounterTypeResponse;
import com.mindconnect.domain.encountertype.port.repository.EncounterTypeRepository;

public class ListEncounterTypeUseCase {

    private final EncounterTypeRepository repository;

    public ListEncounterTypeUseCase(EncounterTypeRepository repository) {
        this.repository = repository;
    }

    public List<EncounterTypeResponse> execute() {
        return repository.findAll()
                .stream()
                .map(EncounterTypeResponse::fromDomain)
                .toList();
    }
}
