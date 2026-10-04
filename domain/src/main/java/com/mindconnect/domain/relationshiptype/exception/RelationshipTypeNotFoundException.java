package com.mindconnect.domain.relationshiptype.exception;

import com.mindconnect.domain.common.exception.DomainException;
import com.mindconnect.domain.relationshiptype.model.valueobject.RelationshipTypeId;

public class RelationshipTypeNotFoundException extends DomainException {

    private static final long serialVersionUID = 1L;

    public RelationshipTypeNotFoundException(RelationshipTypeId id) {
        super("RelationshipType with id " + id.value() + " was not found.");
    }
}
