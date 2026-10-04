package com.mindconnect.application.treatmentstatus.usecase;

import java.util.List;

import com.mindconnect.application.treatmentstatus.dto.TreatmentStatusResponse;
import com.mindconnect.domain.treatmentstatus.port.repository.TreatmentStatusRepository;

public class ListTreatmentStatusUseCase {

    private final TreatmentStatusRepository repository;

    public ListTreatmentStatusUseCase(TreatmentStatusRepository repository) {
        this.repository = repository;
    }

    public List<TreatmentStatusResponse> execute() {
        return repository.findAll()
                .stream()
                .map(TreatmentStatusResponse::fromDomain)
                .toList();
    }
}
