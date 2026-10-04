package com.mindconnect.application.gender.usecase;

import com.mindconnect.application.gender.command.UpdateGenderCommand;
import com.mindconnect.application.gender.dto.GenderResponse;
import com.mindconnect.application.gender.exception.GenderNotFoundApplicationException;
import com.mindconnect.domain.gender.model.aggregate.Gender;
import com.mindconnect.domain.gender.port.repository.GenderRepository;

public class UpdateGenderUseCase {

    private final GenderRepository repository;

    public UpdateGenderUseCase(GenderRepository repository) {
        this.repository = repository;
    }

    public GenderResponse execute(UpdateGenderCommand command) {
        Gender aggregate = repository.findById(command.id())
                .orElseThrow(() -> new GenderNotFoundApplicationException(command.id()));
        aggregate.update(
                command.description());
        Gender saved = repository.save(aggregate);
        return GenderResponse.fromDomain(saved);
    }
}
