package com.mindconnect.infrastructure.risklevel.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import com.mindconnect.domain.risklevel.model.aggregate.RiskLevel;
import com.mindconnect.domain.risklevel.model.valueobject.RiskLevelId;
import com.mindconnect.domain.risklevel.port.repository.RiskLevelRepository;
import com.mindconnect.infrastructure.risklevel.adapters.out.persistence.mappers.RiskLevelPersistenceMapper;

/**
 * Adaptador: implementa el puerto del dominio usando Spring Data y el mapper.
 */
public class RiskLevelRepositoryAdapter implements RiskLevelRepository {

    private final RiskLevelJpaRepository jpaRepository;
    private final RiskLevelPersistenceMapper mapper;

    public RiskLevelRepositoryAdapter(RiskLevelJpaRepository jpaRepository, RiskLevelPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public RiskLevel save(RiskLevel aggregate) {
        return mapper.toDomain(jpaRepository.save(mapper.toJpa(aggregate)));
    }

    @Override
    public Optional<RiskLevel> findById(RiskLevelId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    public List<RiskLevel> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public void delete(RiskLevel aggregate) {
        jpaRepository.deleteById(aggregate.id().value());
    }
}
