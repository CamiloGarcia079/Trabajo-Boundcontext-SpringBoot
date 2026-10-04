package com.mindconnect.application.relationshiptype.exception;

import com.mindconnect.application.common.exception.ApplicationException;
import com.mindconnect.domain.relationshiptype.model.valueobject.RelationshipTypeId;

public class RelationshipTypeNotFoundApplicationException extends ApplicationException {

    private static final long serialVersionUID = 1L;

    public RelationshipTypeNotFoundApplicationException(RelationshipTypeId id) {
        super("RelationshipType with id " + id.value() + " was not found.");
    }
}
