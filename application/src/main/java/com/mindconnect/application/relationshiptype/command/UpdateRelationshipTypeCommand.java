package com.mindconnect.application.relationshiptype.command;


import com.mindconnect.domain.relationshiptype.model.valueobject.RelationshipTypeId;

public record UpdateRelationshipTypeCommand(
        RelationshipTypeId id,
        String description
) {
}
