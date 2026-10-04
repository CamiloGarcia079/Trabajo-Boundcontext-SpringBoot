package com.mindconnect.application.relationshiptype.usecase;

import com.mindconnect.application.relationshiptype.command.UpdateRelationshipTypeCommand;
import com.mindconnect.application.relationshiptype.dto.RelationshipTypeResponse;
import com.mindconnect.application.relationshiptype.exception.RelationshipTypeNotFoundApplicationException;
import com.mindconnect.domain.relationshiptype.model.aggregate.RelationshipType;
import com.mindconnect.domain.relationshiptype.port.repository.RelationshipTypeRepository;

public class UpdateRelationshipTypeUseCase {

    private final RelationshipTypeRepository repository;

    public UpdateRelationshipTypeUseCase(RelationshipTypeRepository repository) {
        this.repository = repository;
    }

    public RelationshipTypeResponse execute(UpdateRelationshipTypeCommand command) {
        RelationshipType aggregate = repository.findById(command.id())
                .orElseThrow(() -> new RelationshipTypeNotFoundApplicationException(command.id()));
        aggregate.update(
                command.description());
        RelationshipType saved = repository.save(aggregate);
        return RelationshipTypeResponse.fromDomain(saved);
    }
}
