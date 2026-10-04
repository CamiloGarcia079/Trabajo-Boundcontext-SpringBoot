package com.mindconnect.domain.professionalstudy.exception;

import com.mindconnect.domain.common.exception.DomainException;
import com.mindconnect.domain.professionalstudy.model.valueobject.ProfessionalStudyId;

public class ProfessionalStudyNotFoundException extends DomainException {

    private static final long serialVersionUID = 1L;

    public ProfessionalStudyNotFoundException(ProfessionalStudyId id) {
        super("ProfessionalStudy with id " + id.value() + " was not found.");
    }
}
