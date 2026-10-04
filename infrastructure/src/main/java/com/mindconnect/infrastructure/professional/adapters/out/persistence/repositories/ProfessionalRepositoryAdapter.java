package com.mindconnect.infrastructure.professional.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import com.mindconnect.domain.professional.model.aggregate.Professional;
import com.mindconnect.domain.professional.model.valueobject.ProfessionalId;
import com.mindconnect.domain.professional.port.repository.ProfessionalRepository;
import com.mindconnect.infrastructure.professional.adapters.out.persistence.mappers.ProfessionalPersistenceMapper;

/**
 * Adaptador: implementa el puerto del dominio usando Spring Data y el mapper.
 */
public class ProfessionalRepositoryAdapter implements ProfessionalRepository {

    private final ProfessionalJpaRepository jpaRepository;
    private final ProfessionalPersistenceMapper mapper;

    public ProfessionalRepositoryAdapter(ProfessionalJpaRepository jpaRepository, ProfessionalPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Professional save(Professional aggregate) {
        return mapper.toDomain(jpaRepository.save(mapper.toJpa(aggregate)));
    }

    @Override
    public Optional<Professional> findById(ProfessionalId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    public List<Professional> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public void delete(Professional aggregate) {
        jpaRepository.deleteById(aggregate.id().value());
    }
}
