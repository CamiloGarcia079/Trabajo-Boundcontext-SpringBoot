package com.mindconnect.application.relationshiptype.usecase;

import com.mindconnect.application.relationshiptype.dto.RelationshipTypeResponse;
import com.mindconnect.application.relationshiptype.exception.RelationshipTypeNotFoundApplicationException;
import com.mindconnect.domain.relationshiptype.model.valueobject.RelationshipTypeId;
import com.mindconnect.domain.relationshiptype.port.repository.RelationshipTypeRepository;

public class GetRelationshipTypeByIdUseCase {

    private final RelationshipTypeRepository repository;

    public GetRelationshipTypeByIdUseCase(RelationshipTypeRepository repository) {
        this.repository = repository;
    }

    public RelationshipTypeResponse execute(RelationshipTypeId id) {
        return repository.findById(id)
                .map(RelationshipTypeResponse::fromDomain)
                .orElseThrow(() -> new RelationshipTypeNotFoundApplicationException(id));
    }
}
