package com.mindconnect.domain.professional.exception;

import com.mindconnect.domain.common.exception.DomainException;
import com.mindconnect.domain.professional.model.valueobject.ProfessionalId;

public class ProfessionalNotFoundException extends DomainException {

    private static final long serialVersionUID = 1L;

    public ProfessionalNotFoundException(ProfessionalId id) {
        super("Professional with id " + id.value() + " was not found.");
    }
}
