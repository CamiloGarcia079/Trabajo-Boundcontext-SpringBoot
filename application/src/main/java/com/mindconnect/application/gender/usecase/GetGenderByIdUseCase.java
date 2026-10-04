package com.mindconnect.application.gender.usecase;

import com.mindconnect.application.gender.dto.GenderResponse;
import com.mindconnect.application.gender.exception.GenderNotFoundApplicationException;
import com.mindconnect.domain.gender.model.valueobject.GenderId;
import com.mindconnect.domain.gender.port.repository.GenderRepository;

public class GetGenderByIdUseCase {

    private final GenderRepository repository;

    public GetGenderByIdUseCase(GenderRepository repository) {
        this.repository = repository;
    }

    public GenderResponse execute(GenderId id) {
        return repository.findById(id)
                .map(GenderResponse::fromDomain)
                .orElseThrow(() -> new GenderNotFoundApplicationException(id));
    }
}
