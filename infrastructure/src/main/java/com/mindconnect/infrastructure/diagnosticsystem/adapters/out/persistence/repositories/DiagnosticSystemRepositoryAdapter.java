package com.mindconnect.infrastructure.diagnosticsystem.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import com.mindconnect.domain.diagnosticsystem.model.aggregate.DiagnosticSystem;
import com.mindconnect.domain.diagnosticsystem.model.valueobject.DiagnosticSystemId;
import com.mindconnect.domain.diagnosticsystem.port.repository.DiagnosticSystemRepository;
import com.mindconnect.infrastructure.diagnosticsystem.adapters.out.persistence.mappers.DiagnosticSystemPersistenceMapper;

/**
 * Adaptador: implementa el puerto del dominio usando Spring Data y el mapper.
 */
public class DiagnosticSystemRepositoryAdapter implements DiagnosticSystemRepository {

    private final DiagnosticSystemJpaRepository jpaRepository;
    private final DiagnosticSystemPersistenceMapper mapper;

    public DiagnosticSystemRepositoryAdapter(DiagnosticSystemJpaRepository jpaRepository, DiagnosticSystemPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public DiagnosticSystem save(DiagnosticSystem aggregate) {
        return mapper.toDomain(jpaRepository.save(mapper.toJpa(aggregate)));
    }

    @Override
    public Optional<DiagnosticSystem> findById(DiagnosticSystemId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    public List<DiagnosticSystem> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public void delete(DiagnosticSystem aggregate) {
        jpaRepository.deleteById(aggregate.id().value());
    }
}
