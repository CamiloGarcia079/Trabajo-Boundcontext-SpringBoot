package com.mindconnect.application.treatmentgoal.usecase;

import java.util.List;

import com.mindconnect.application.treatmentgoal.dto.TreatmentGoalResponse;
import com.mindconnect.domain.treatmentgoal.port.repository.TreatmentGoalRepository;

public class ListTreatmentGoalUseCase {

    private final TreatmentGoalRepository repository;

    public ListTreatmentGoalUseCase(TreatmentGoalRepository repository) {
        this.repository = repository;
    }

    public List<TreatmentGoalResponse> execute() {
        return repository.findAll()
                .stream()
                .map(TreatmentGoalResponse::fromDomain)
                .toList();
    }
}
