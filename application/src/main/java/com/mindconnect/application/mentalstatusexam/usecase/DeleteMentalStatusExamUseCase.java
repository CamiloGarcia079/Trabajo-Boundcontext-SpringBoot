package com.mindconnect.application.mentalstatusexam.usecase;

import java.time.LocalDateTime;

import com.mindconnect.application.mentalstatusexam.exception.MentalStatusExamNotFoundApplicationException;
import com.mindconnect.domain.mentalstatusexam.event.MentalStatusExamDeletedEvent;
import com.mindconnect.domain.mentalstatusexam.model.valueobject.MentalStatusExamId;
import com.mindconnect.domain.mentalstatusexam.port.repository.MentalStatusExamRepository;

public class DeleteMentalStatusExamUseCase {

    private final MentalStatusExamRepository repository;

    public DeleteMentalStatusExamUseCase(MentalStatusExamRepository repository) {
        this.repository = repository;
    }

    public MentalStatusExamDeletedEvent execute(MentalStatusExamId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new MentalStatusExamNotFoundApplicationException(id));

        repository.delete(aggregate);

        return new MentalStatusExamDeletedEvent(id, LocalDateTime.now());
    }
}
