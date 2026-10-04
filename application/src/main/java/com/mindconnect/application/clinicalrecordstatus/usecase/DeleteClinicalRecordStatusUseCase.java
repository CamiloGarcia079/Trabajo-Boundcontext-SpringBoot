package com.mindconnect.application.clinicalrecordstatus.usecase;

import java.time.LocalDateTime;

import com.mindconnect.application.clinicalrecordstatus.exception.ClinicalRecordStatusNotFoundApplicationException;
import com.mindconnect.domain.clinicalrecordstatus.event.ClinicalRecordStatusDeletedEvent;
import com.mindconnect.domain.clinicalrecordstatus.model.valueobject.ClinicalRecordStatusId;
import com.mindconnect.domain.clinicalrecordstatus.port.repository.ClinicalRecordStatusRepository;

public class DeleteClinicalRecordStatusUseCase {

    private final ClinicalRecordStatusRepository repository;

    public DeleteClinicalRecordStatusUseCase(ClinicalRecordStatusRepository repository) {
        this.repository = repository;
    }

    public ClinicalRecordStatusDeletedEvent execute(ClinicalRecordStatusId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new ClinicalRecordStatusNotFoundApplicationException(id));

        repository.delete(aggregate);

        return new ClinicalRecordStatusDeletedEvent(id, LocalDateTime.now());
    }
}
