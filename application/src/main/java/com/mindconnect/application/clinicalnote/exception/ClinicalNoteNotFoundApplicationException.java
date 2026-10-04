package com.mindconnect.application.clinicalnote.exception;

import com.mindconnect.application.common.exception.ApplicationException;
import com.mindconnect.domain.clinicalnote.model.valueobject.ClinicalNoteId;

public class ClinicalNoteNotFoundApplicationException extends ApplicationException {

    private static final long serialVersionUID = 1L;

    public ClinicalNoteNotFoundApplicationException(ClinicalNoteId id) {
        super("ClinicalNote with id " + id.value() + " was not found.");
    }
}
