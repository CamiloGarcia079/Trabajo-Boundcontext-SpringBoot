package com.mindconnect.application.clinicalrecordstatus.usecase;

import com.mindconnect.application.clinicalrecordstatus.dto.ClinicalRecordStatusResponse;
import com.mindconnect.application.clinicalrecordstatus.exception.ClinicalRecordStatusNotFoundApplicationException;
import com.mindconnect.domain.clinicalrecordstatus.model.valueobject.ClinicalRecordStatusId;
import com.mindconnect.domain.clinicalrecordstatus.port.repository.ClinicalRecordStatusRepository;

public class GetClinicalRecordStatusByIdUseCase {

    private final ClinicalRecordStatusRepository repository;

    public GetClinicalRecordStatusByIdUseCase(ClinicalRecordStatusRepository repository) {
        this.repository = repository;
    }

    public ClinicalRecordStatusResponse execute(ClinicalRecordStatusId id) {
        return repository.findById(id)
                .map(ClinicalRecordStatusResponse::fromDomain)
                .orElseThrow(() -> new ClinicalRecordStatusNotFoundApplicationException(id));
    }
}
