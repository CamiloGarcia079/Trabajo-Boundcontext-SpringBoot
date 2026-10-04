package com.mindconnect.application.clinicalrecord.usecase;

import java.util.List;

import com.mindconnect.application.clinicalrecord.dto.ClinicalRecordResponse;
import com.mindconnect.domain.clinicalrecord.port.repository.ClinicalRecordRepository;

public class ListClinicalRecordUseCase {

    private final ClinicalRecordRepository repository;

    public ListClinicalRecordUseCase(ClinicalRecordRepository repository) {
        this.repository = repository;
    }

    public List<ClinicalRecordResponse> execute() {
        return repository.findAll()
                .stream()
                .map(ClinicalRecordResponse::fromDomain)
                .toList();
    }
}
