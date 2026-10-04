package com.mindconnect.domain.escalationstatus.exception;

import com.mindconnect.domain.common.exception.DomainException;
import com.mindconnect.domain.escalationstatus.model.valueobject.EscalationStatusId;

public class EscalationStatusNotFoundException extends DomainException {

    private static final long serialVersionUID = 1L;

    public EscalationStatusNotFoundException(EscalationStatusId id) {
        super("EscalationStatus with id " + id.value() + " was not found.");
    }
}
