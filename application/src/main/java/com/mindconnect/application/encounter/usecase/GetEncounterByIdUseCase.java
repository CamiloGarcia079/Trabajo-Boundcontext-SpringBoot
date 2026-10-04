package com.mindconnect.application.encounter.usecase;

import com.mindconnect.application.encounter.dto.EncounterResponse;
import com.mindconnect.application.encounter.exception.EncounterNotFoundApplicationException;
import com.mindconnect.domain.encounter.model.valueobject.EncounterId;
import com.mindconnect.domain.encounter.port.repository.EncounterRepository;

public class GetEncounterByIdUseCase {

    private final EncounterRepository repository;

    public GetEncounterByIdUseCase(EncounterRepository repository) {
        this.repository = repository;
    }

    public EncounterResponse execute(EncounterId id) {
        return repository.findById(id)
                .map(EncounterResponse::fromDomain)
                .orElseThrow(() -> new EncounterNotFoundApplicationException(id));
    }
}
