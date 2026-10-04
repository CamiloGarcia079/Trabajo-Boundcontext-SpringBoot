package com.mindconnect.application.clinicalnote.usecase;

import com.mindconnect.application.clinicalnote.dto.ClinicalNoteResponse;
import com.mindconnect.application.clinicalnote.exception.ClinicalNoteNotFoundApplicationException;
import com.mindconnect.domain.clinicalnote.model.valueobject.ClinicalNoteId;
import com.mindconnect.domain.clinicalnote.port.repository.ClinicalNoteRepository;

public class GetClinicalNoteByIdUseCase {

    private final ClinicalNoteRepository repository;

    public GetClinicalNoteByIdUseCase(ClinicalNoteRepository repository) {
        this.repository = repository;
    }

    public ClinicalNoteResponse execute(ClinicalNoteId id) {
        return repository.findById(id)
                .map(ClinicalNoteResponse::fromDomain)
                .orElseThrow(() -> new ClinicalNoteNotFoundApplicationException(id));
    }
}
