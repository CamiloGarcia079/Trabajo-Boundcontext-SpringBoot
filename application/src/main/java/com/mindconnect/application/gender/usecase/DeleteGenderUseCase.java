package com.mindconnect.application.gender.usecase;

import java.time.LocalDateTime;

import com.mindconnect.application.gender.exception.GenderNotFoundApplicationException;
import com.mindconnect.domain.gender.event.GenderDeletedEvent;
import com.mindconnect.domain.gender.model.valueobject.GenderId;
import com.mindconnect.domain.gender.port.repository.GenderRepository;

public class DeleteGenderUseCase {

    private final GenderRepository repository;

    public DeleteGenderUseCase(GenderRepository repository) {
        this.repository = repository;
    }

    public GenderDeletedEvent execute(GenderId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new GenderNotFoundApplicationException(id));

        repository.delete(aggregate);

        return new GenderDeletedEvent(id, LocalDateTime.now());
    }
}
