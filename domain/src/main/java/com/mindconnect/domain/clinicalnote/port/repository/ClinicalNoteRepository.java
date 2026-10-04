package com.mindconnect.domain.clinicalnote.port.repository;

import java.util.List;
import java.util.Optional;

import com.mindconnect.domain.clinicalnote.model.aggregate.ClinicalNote;
import com.mindconnect.domain.clinicalnote.model.valueobject.ClinicalNoteId;

/**
 * Puerto de salida: lo que el dominio necesita para guardar y buscar ClinicalNote.
 * Lo implementa la infraestructura.
 */
public interface ClinicalNoteRepository {

    ClinicalNote save(ClinicalNote aggregate);

    Optional<ClinicalNote> findById(ClinicalNoteId id);

    List<ClinicalNote> findAll();

    void delete(ClinicalNote aggregate);
}
