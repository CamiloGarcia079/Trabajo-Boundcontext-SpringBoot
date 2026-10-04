package com.mindconnect.application.clinicalrecord.usecase;

import java.time.LocalDateTime;

import com.mindconnect.application.clinicalrecord.exception.ClinicalRecordNotFoundApplicationException;
import com.mindconnect.domain.clinicalrecord.event.ClinicalRecordDeletedEvent;
import com.mindconnect.domain.clinicalrecord.model.valueobject.ClinicalRecordId;
import com.mindconnect.domain.clinicalrecord.port.repository.ClinicalRecordRepository;

public class DeleteClinicalRecordUseCase {

    private final ClinicalRecordRepository repository;

    public DeleteClinicalRecordUseCase(ClinicalRecordRepository repository) {
        this.repository = repository;
    }

    public ClinicalRecordDeletedEvent execute(ClinicalRecordId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new ClinicalRecordNotFoundApplicationException(id));

        repository.delete(aggregate);

        return new ClinicalRecordDeletedEvent(id, LocalDateTime.now());
    }
}
