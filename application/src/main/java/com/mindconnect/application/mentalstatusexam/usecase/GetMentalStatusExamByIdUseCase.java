package com.mindconnect.application.mentalstatusexam.usecase;

import com.mindconnect.application.mentalstatusexam.dto.MentalStatusExamResponse;
import com.mindconnect.application.mentalstatusexam.exception.MentalStatusExamNotFoundApplicationException;
import com.mindconnect.domain.mentalstatusexam.model.valueobject.MentalStatusExamId;
import com.mindconnect.domain.mentalstatusexam.port.repository.MentalStatusExamRepository;

public class GetMentalStatusExamByIdUseCase {

    private final MentalStatusExamRepository repository;

    public GetMentalStatusExamByIdUseCase(MentalStatusExamRepository repository) {
        this.repository = repository;
    }

    public MentalStatusExamResponse execute(MentalStatusExamId id) {
        return repository.findById(id)
                .map(MentalStatusExamResponse::fromDomain)
                .orElseThrow(() -> new MentalStatusExamNotFoundApplicationException(id));
    }
}
