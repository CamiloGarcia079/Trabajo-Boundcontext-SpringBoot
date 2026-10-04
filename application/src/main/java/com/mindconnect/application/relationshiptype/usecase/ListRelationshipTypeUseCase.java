package com.mindconnect.application.relationshiptype.usecase;

import java.util.List;

import com.mindconnect.application.relationshiptype.dto.RelationshipTypeResponse;
import com.mindconnect.domain.relationshiptype.port.repository.RelationshipTypeRepository;

public class ListRelationshipTypeUseCase {

    private final RelationshipTypeRepository repository;

    public ListRelationshipTypeUseCase(RelationshipTypeRepository repository) {
        this.repository = repository;
    }

    public List<RelationshipTypeResponse> execute() {
        return repository.findAll()
                .stream()
                .map(RelationshipTypeResponse::fromDomain)
                .toList();
    }
}
