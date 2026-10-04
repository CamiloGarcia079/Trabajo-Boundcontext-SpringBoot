package com.mindconnect.application.mentalstatusexam.usecase;

import java.util.List;

import com.mindconnect.application.mentalstatusexam.dto.MentalStatusExamResponse;
import com.mindconnect.domain.mentalstatusexam.port.repository.MentalStatusExamRepository;

public class ListMentalStatusExamUseCase {

    private final MentalStatusExamRepository repository;

    public ListMentalStatusExamUseCase(MentalStatusExamRepository repository) {
        this.repository = repository;
    }

    public List<MentalStatusExamResponse> execute() {
        return repository.findAll()
                .stream()
                .map(MentalStatusExamResponse::fromDomain)
                .toList();
    }
}
