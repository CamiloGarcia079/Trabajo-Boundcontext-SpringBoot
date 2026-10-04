package com.mindconnect.infrastructure.relationshiptype.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import com.mindconnect.domain.relationshiptype.model.aggregate.RelationshipType;
import com.mindconnect.domain.relationshiptype.model.valueobject.RelationshipTypeId;
import com.mindconnect.domain.relationshiptype.port.repository.RelationshipTypeRepository;
import com.mindconnect.infrastructure.relationshiptype.adapters.out.persistence.mappers.RelationshipTypePersistenceMapper;

/**
 * Adaptador: implementa el puerto del dominio usando Spring Data y el mapper.
 */
public class RelationshipTypeRepositoryAdapter implements RelationshipTypeRepository {

    private final RelationshipTypeJpaRepository jpaRepository;
    private final RelationshipTypePersistenceMapper mapper;

    public RelationshipTypeRepositoryAdapter(RelationshipTypeJpaRepository jpaRepository, RelationshipTypePersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public RelationshipType save(RelationshipType aggregate) {
        return mapper.toDomain(jpaRepository.save(mapper.toJpa(aggregate)));
    }

    @Override
    public Optional<RelationshipType> findById(RelationshipTypeId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    public List<RelationshipType> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public void delete(RelationshipType aggregate) {
        jpaRepository.deleteById(aggregate.id().value());
    }
}
