package com.mindconnect.application.escalationstatus.exception;

import com.mindconnect.application.common.exception.ApplicationException;
import com.mindconnect.domain.escalationstatus.model.valueobject.EscalationStatusId;

public class EscalationStatusNotFoundApplicationException extends ApplicationException {

    private static final long serialVersionUID = 1L;

    public EscalationStatusNotFoundApplicationException(EscalationStatusId id) {
        super("EscalationStatus with id " + id.value() + " was not found.");
    }
}
