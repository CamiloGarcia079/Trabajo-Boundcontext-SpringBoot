package com.mindconnect.application.clinicalrecord.usecase;

import com.mindconnect.application.clinicalrecord.dto.ClinicalRecordResponse;
import com.mindconnect.application.clinicalrecord.exception.ClinicalRecordNotFoundApplicationException;
import com.mindconnect.domain.clinicalrecord.model.valueobject.ClinicalRecordId;
import com.mindconnect.domain.clinicalrecord.port.repository.ClinicalRecordRepository;

public class GetClinicalRecordByIdUseCase {

    private final ClinicalRecordRepository repository;

    public GetClinicalRecordByIdUseCase(ClinicalRecordRepository repository) {
        this.repository = repository;
    }

    public ClinicalRecordResponse execute(ClinicalRecordId id) {
        return repository.findById(id)
                .map(ClinicalRecordResponse::fromDomain)
                .orElseThrow(() -> new ClinicalRecordNotFoundApplicationException(id));
    }
}
