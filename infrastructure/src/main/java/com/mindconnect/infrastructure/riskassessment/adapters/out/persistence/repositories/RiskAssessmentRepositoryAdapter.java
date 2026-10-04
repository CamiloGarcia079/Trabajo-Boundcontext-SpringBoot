package com.mindconnect.infrastructure.riskassessment.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import com.mindconnect.domain.riskassessment.model.aggregate.RiskAssessment;
import com.mindconnect.domain.riskassessment.model.valueobject.RiskAssessmentId;
import com.mindconnect.domain.riskassessment.port.repository.RiskAssessmentRepository;
import com.mindconnect.infrastructure.riskassessment.adapters.out.persistence.mappers.RiskAssessmentPersistenceMapper;

/**
 * Adaptador: implementa el puerto del dominio usando Spring Data y el mapper.
 */
public class RiskAssessmentRepositoryAdapter implements RiskAssessmentRepository {

    private final RiskAssessmentJpaRepository jpaRepository;
    private final RiskAssessmentPersistenceMapper mapper;

    public RiskAssessmentRepositoryAdapter(RiskAssessmentJpaRepository jpaRepository, RiskAssessmentPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public RiskAssessment save(RiskAssessment aggregate) {
        return mapper.toDomain(jpaRepository.save(mapper.toJpa(aggregate)));
    }

    @Override
    public Optional<RiskAssessment> findById(RiskAssessmentId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    public List<RiskAssessment> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public void delete(RiskAssessment aggregate) {
        jpaRepository.deleteById(aggregate.id().value());
    }
}
