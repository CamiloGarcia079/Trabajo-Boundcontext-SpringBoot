package com.mindconnect.infrastructure.assessmenttype.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import com.mindconnect.domain.assessmenttype.model.aggregate.AssessmentType;
import com.mindconnect.domain.assessmenttype.model.valueobject.AssessmentTypeId;
import com.mindconnect.domain.assessmenttype.port.repository.AssessmentTypeRepository;
import com.mindconnect.infrastructure.assessmenttype.adapters.out.persistence.mappers.AssessmentTypePersistenceMapper;

/**
 * Adaptador: implementa el puerto del dominio usando Spring Data y el mapper.
 */
public class AssessmentTypeRepositoryAdapter implements AssessmentTypeRepository {

    private final AssessmentTypeJpaRepository jpaRepository;
    private final AssessmentTypePersistenceMapper mapper;

    public AssessmentTypeRepositoryAdapter(AssessmentTypeJpaRepository jpaRepository, AssessmentTypePersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public AssessmentType save(AssessmentType aggregate) {
        return mapper.toDomain(jpaRepository.save(mapper.toJpa(aggregate)));
    }

    @Override
    public Optional<AssessmentType> findById(AssessmentTypeId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    public List<AssessmentType> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public void delete(AssessmentType aggregate) {
        jpaRepository.deleteById(aggregate.id().value());
    }
}
