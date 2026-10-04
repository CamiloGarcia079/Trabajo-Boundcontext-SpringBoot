package com.mindconnect.domain.relationshiptype.port.repository;

import java.util.List;
import java.util.Optional;

import com.mindconnect.domain.relationshiptype.model.aggregate.RelationshipType;
import com.mindconnect.domain.relationshiptype.model.valueobject.RelationshipTypeId;

/**
 * Puerto de salida: lo que el dominio necesita para guardar y buscar RelationshipType.
 * Lo implementa la infraestructura.
 */
public interface RelationshipTypeRepository {

    RelationshipType save(RelationshipType aggregate);

    Optional<RelationshipType> findById(RelationshipTypeId id);

    List<RelationshipType> findAll();

    void delete(RelationshipType aggregate);
}
