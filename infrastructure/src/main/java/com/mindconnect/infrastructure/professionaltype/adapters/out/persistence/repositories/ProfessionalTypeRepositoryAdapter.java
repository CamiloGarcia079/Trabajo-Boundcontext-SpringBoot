package com.mindconnect.infrastructure.professionaltype.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import com.mindconnect.domain.professionaltype.model.aggregate.ProfessionalType;
import com.mindconnect.domain.professionaltype.model.valueobject.ProfessionalTypeId;
import com.mindconnect.domain.professionaltype.port.repository.ProfessionalTypeRepository;
import com.mindconnect.infrastructure.professionaltype.adapters.out.persistence.mappers.ProfessionalTypePersistenceMapper;

/**
 * Adaptador: implementa el puerto del dominio usando Spring Data y el mapper.
 */
public class ProfessionalTypeRepositoryAdapter implements ProfessionalTypeRepository {

    private final ProfessionalTypeJpaRepository jpaRepository;
    private final ProfessionalTypePersistenceMapper mapper;

    public ProfessionalTypeRepositoryAdapter(ProfessionalTypeJpaRepository jpaRepository, ProfessionalTypePersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public ProfessionalType save(ProfessionalType aggregate) {
        return mapper.toDomain(jpaRepository.save(mapper.toJpa(aggregate)));
    }

    @Override
    public Optional<ProfessionalType> findById(ProfessionalTypeId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    public List<ProfessionalType> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public void delete(ProfessionalType aggregate) {
        jpaRepository.deleteById(aggregate.id().value());
    }
}
