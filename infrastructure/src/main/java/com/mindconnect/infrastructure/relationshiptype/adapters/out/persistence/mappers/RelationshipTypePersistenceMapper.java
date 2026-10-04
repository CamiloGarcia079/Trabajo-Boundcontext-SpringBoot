package com.mindconnect.infrastructure.relationshiptype.adapters.out.persistence.mappers;

import com.mindconnect.domain.relationshiptype.model.aggregate.RelationshipType;
import com.mindconnect.domain.relationshiptype.model.valueobject.RelationshipTypeId;
import com.mindconnect.infrastructure.relationshiptype.adapters.out.persistence.entity.RelationshipTypeJpaEntity;

/**
 * Convierte entre el agregado de dominio y la entidad JPA de relationship_types.
 */
public class RelationshipTypePersistenceMapper {

    public RelationshipTypeJpaEntity toJpa(RelationshipType domain) {
        if (domain == null) {
            return null;
        }

        RelationshipTypeJpaEntity jpa = new RelationshipTypeJpaEntity();
        jpa.setId(domain.id().value());
        jpa.setDescription(domain.description());

        return jpa;
    }

    public RelationshipType toDomain(RelationshipTypeJpaEntity jpa) {
        if (jpa == null) {
            return null;
        }

        return RelationshipType.restore(
                new RelationshipTypeId(jpa.getId()),
                jpa.getDescription());
    }
}
