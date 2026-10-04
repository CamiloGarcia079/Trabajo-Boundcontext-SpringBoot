package com.mindconnect.infrastructure.gender.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import com.mindconnect.domain.gender.model.aggregate.Gender;
import com.mindconnect.domain.gender.model.valueobject.GenderId;
import com.mindconnect.domain.gender.port.repository.GenderRepository;
import com.mindconnect.infrastructure.gender.adapters.out.persistence.mappers.GenderPersistenceMapper;

/**
 * Adaptador: implementa el puerto del dominio usando Spring Data y el mapper.
 */
public class GenderRepositoryAdapter implements GenderRepository {

    private final GenderJpaRepository jpaRepository;
    private final GenderPersistenceMapper mapper;

    public GenderRepositoryAdapter(GenderJpaRepository jpaRepository, GenderPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Gender save(Gender aggregate) {
        return mapper.toDomain(jpaRepository.save(mapper.toJpa(aggregate)));
    }

    @Override
    public Optional<Gender> findById(GenderId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    public List<Gender> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public void delete(Gender aggregate) {
        jpaRepository.deleteById(aggregate.id().value());
    }
}
