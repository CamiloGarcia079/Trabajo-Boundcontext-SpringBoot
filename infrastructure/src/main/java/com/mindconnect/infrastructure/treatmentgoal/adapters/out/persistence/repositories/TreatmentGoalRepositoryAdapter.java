package com.mindconnect.infrastructure.treatmentgoal.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import com.mindconnect.domain.treatmentgoal.model.aggregate.TreatmentGoal;
import com.mindconnect.domain.treatmentgoal.model.valueobject.TreatmentGoalId;
import com.mindconnect.domain.treatmentgoal.port.repository.TreatmentGoalRepository;
import com.mindconnect.infrastructure.treatmentgoal.adapters.out.persistence.mappers.TreatmentGoalPersistenceMapper;

/**
 * Adaptador: implementa el puerto del dominio usando Spring Data y el mapper.
 */
public class TreatmentGoalRepositoryAdapter implements TreatmentGoalRepository {

    private final TreatmentGoalJpaRepository jpaRepository;
    private final TreatmentGoalPersistenceMapper mapper;

    public TreatmentGoalRepositoryAdapter(TreatmentGoalJpaRepository jpaRepository, TreatmentGoalPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public TreatmentGoal save(TreatmentGoal aggregate) {
        return mapper.toDomain(jpaRepository.save(mapper.toJpa(aggregate)));
    }

    @Override
    public Optional<TreatmentGoal> findById(TreatmentGoalId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    public List<TreatmentGoal> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public void delete(TreatmentGoal aggregate) {
        jpaRepository.deleteById(aggregate.id().value());
    }
}
