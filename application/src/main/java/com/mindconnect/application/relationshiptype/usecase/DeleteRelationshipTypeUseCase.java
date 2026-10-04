package com.mindconnect.application.relationshiptype.usecase;

import java.time.LocalDateTime;

import com.mindconnect.application.relationshiptype.exception.RelationshipTypeNotFoundApplicationException;
import com.mindconnect.domain.relationshiptype.event.RelationshipTypeDeletedEvent;
import com.mindconnect.domain.relationshiptype.model.valueobject.RelationshipTypeId;
import com.mindconnect.domain.relationshiptype.port.repository.RelationshipTypeRepository;

public class DeleteRelationshipTypeUseCase {

    private final RelationshipTypeRepository repository;

    public DeleteRelationshipTypeUseCase(RelationshipTypeRepository repository) {
        this.repository = repository;
    }

    public RelationshipTypeDeletedEvent execute(RelationshipTypeId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new RelationshipTypeNotFoundApplicationException(id));

        repository.delete(aggregate);

        return new RelationshipTypeDeletedEvent(id, LocalDateTime.now());
    }
}
