package com.mindconnect.infrastructure.treatmentplan.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import com.mindconnect.domain.treatmentplan.model.aggregate.TreatmentPlan;
import com.mindconnect.domain.treatmentplan.model.valueobject.TreatmentPlanId;
import com.mindconnect.domain.treatmentplan.port.repository.TreatmentPlanRepository;
import com.mindconnect.infrastructure.treatmentplan.adapters.out.persistence.mappers.TreatmentPlanPersistenceMapper;

/**
 * Adaptador: implementa el puerto del dominio usando Spring Data y el mapper.
 */
public class TreatmentPlanRepositoryAdapter implements TreatmentPlanRepository {

    private final TreatmentPlanJpaRepository jpaRepository;
    private final TreatmentPlanPersistenceMapper mapper;

    public TreatmentPlanRepositoryAdapter(TreatmentPlanJpaRepository jpaRepository, TreatmentPlanPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public TreatmentPlan save(TreatmentPlan aggregate) {
        return mapper.toDomain(jpaRepository.save(mapper.toJpa(aggregate)));
    }

    @Override
    public Optional<TreatmentPlan> findById(TreatmentPlanId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    public List<TreatmentPlan> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public void delete(TreatmentPlan aggregate) {
        jpaRepository.deleteById(aggregate.id().value());
    }
}
