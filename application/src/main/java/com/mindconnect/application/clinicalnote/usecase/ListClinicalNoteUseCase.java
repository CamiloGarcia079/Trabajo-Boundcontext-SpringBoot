package com.mindconnect.application.clinicalnote.usecase;

import java.util.List;

import com.mindconnect.application.clinicalnote.dto.ClinicalNoteResponse;
import com.mindconnect.domain.clinicalnote.port.repository.ClinicalNoteRepository;

public class ListClinicalNoteUseCase {

    private final ClinicalNoteRepository repository;

    public ListClinicalNoteUseCase(ClinicalNoteRepository repository) {
        this.repository = repository;
    }

    public List<ClinicalNoteResponse> execute() {
        return repository.findAll()
                .stream()
                .map(ClinicalNoteResponse::fromDomain)
                .toList();
    }
}
