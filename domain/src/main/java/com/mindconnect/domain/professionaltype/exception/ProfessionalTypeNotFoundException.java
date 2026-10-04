package com.mindconnect.domain.professionaltype.exception;

import com.mindconnect.domain.common.exception.DomainException;
import com.mindconnect.domain.professionaltype.model.valueobject.ProfessionalTypeId;

public class ProfessionalTypeNotFoundException extends DomainException {

    private static final long serialVersionUID = 1L;

    public ProfessionalTypeNotFoundException(ProfessionalTypeId id) {
        super("ProfessionalType with id " + id.value() + " was not found.");
    }
}
