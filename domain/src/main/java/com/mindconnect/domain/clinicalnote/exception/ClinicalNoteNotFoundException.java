package com.mindconnect.domain.clinicalnote.exception;

import com.mindconnect.domain.common.exception.DomainException;
import com.mindconnect.domain.clinicalnote.model.valueobject.ClinicalNoteId;

public class ClinicalNoteNotFoundException extends DomainException {

    private static final long serialVersionUID = 1L;

    public ClinicalNoteNotFoundException(ClinicalNoteId id) {
        super("ClinicalNote with id " + id.value() + " was not found.");
    }
}
