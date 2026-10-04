package com.mindconnect.application.treatmentgoalstatus.usecase;

import java.util.List;

import com.mindconnect.application.treatmentgoalstatus.dto.TreatmentGoalStatusResponse;
import com.mindconnect.domain.treatmentgoalstatus.port.repository.TreatmentGoalStatusRepository;

public class ListTreatmentGoalStatusUseCase {

    private final TreatmentGoalStatusRepository repository;

    public ListTreatmentGoalStatusUseCase(TreatmentGoalStatusRepository repository) {
        this.repository = repository;
    }

    public List<TreatmentGoalStatusResponse> execute() {
        return repository.findAll()
                .stream()
                .map(TreatmentGoalStatusResponse::fromDomain)
                .toList();
    }
}
