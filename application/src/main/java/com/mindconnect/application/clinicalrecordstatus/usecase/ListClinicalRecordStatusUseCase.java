package com.mindconnect.application.clinicalrecordstatus.usecase;

import java.util.List;

import com.mindconnect.application.clinicalrecordstatus.dto.ClinicalRecordStatusResponse;
import com.mindconnect.domain.clinicalrecordstatus.port.repository.ClinicalRecordStatusRepository;

public class ListClinicalRecordStatusUseCase {

    private final ClinicalRecordStatusRepository repository;

    public ListClinicalRecordStatusUseCase(ClinicalRecordStatusRepository repository) {
        this.repository = repository;
    }

    public List<ClinicalRecordStatusResponse> execute() {
        return repository.findAll()
                .stream()
                .map(ClinicalRecordStatusResponse::fromDomain)
                .toList();
    }
}
