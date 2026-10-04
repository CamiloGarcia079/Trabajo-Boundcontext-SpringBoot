package com.mindconnect.application.relationshiptype.usecase;

import com.mindconnect.application.relationshiptype.command.RegisterRelationshipTypeCommand;
import com.mindconnect.application.relationshiptype.dto.RelationshipTypeResponse;
import com.mindconnect.domain.relationshiptype.model.aggregate.RelationshipType;
import com.mindconnect.domain.relationshiptype.port.repository.RelationshipTypeRepository;

public class RegisterRelationshipTypeUseCase {

    private final RelationshipTypeRepository repository;

    public RegisterRelationshipTypeUseCase(RelationshipTypeRepository repository) {
        this.repository = repository;
    }

    public RelationshipTypeResponse execute(RegisterRelationshipTypeCommand command) {
        RelationshipType aggregate = RelationshipType.register(
                command.description());
        RelationshipType saved = repository.save(aggregate);
        return RelationshipTypeResponse.fromDomain(saved);
    }
}
