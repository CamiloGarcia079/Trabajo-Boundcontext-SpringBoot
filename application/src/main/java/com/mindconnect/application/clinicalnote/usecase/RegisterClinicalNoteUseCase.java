package com.mindconnect.application.clinicalnote.usecase;

import com.mindconnect.application.clinicalnote.command.RegisterClinicalNoteCommand;
import com.mindconnect.application.clinicalnote.dto.ClinicalNoteResponse;
import com.mindconnect.domain.clinicalnote.model.aggregate.ClinicalNote;
import com.mindconnect.domain.clinicalnote.port.repository.ClinicalNoteRepository;

public class RegisterClinicalNoteUseCase {

    private final ClinicalNoteRepository repository;

    public RegisterClinicalNoteUseCase(ClinicalNoteRepository repository) {
        this.repository = repository;
    }

    public ClinicalNoteResponse execute(RegisterClinicalNoteCommand command) {
        ClinicalNote aggregate = ClinicalNote.register(
                command.encounterId(), command.professionalId(), command.subjective(), command.objective(), command.assessment(), command.plan(), command.additionalNotes(), command.signedAt());
        ClinicalNote saved = repository.save(aggregate);
        return ClinicalNoteResponse.fromDomain(saved);
    }
}
