package com.mindconnect.application.clinicalnote.usecase;

import java.time.LocalDateTime;

import com.mindconnect.application.clinicalnote.exception.ClinicalNoteNotFoundApplicationException;
import com.mindconnect.domain.clinicalnote.event.ClinicalNoteDeletedEvent;
import com.mindconnect.domain.clinicalnote.model.valueobject.ClinicalNoteId;
import com.mindconnect.domain.clinicalnote.port.repository.ClinicalNoteRepository;

public class DeleteClinicalNoteUseCase {

    private final ClinicalNoteRepository repository;

    public DeleteClinicalNoteUseCase(ClinicalNoteRepository repository) {
        this.repository = repository;
    }

    public ClinicalNoteDeletedEvent execute(ClinicalNoteId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new ClinicalNoteNotFoundApplicationException(id));

        repository.delete(aggregate);

        return new ClinicalNoteDeletedEvent(id, LocalDateTime.now());
    }
}
