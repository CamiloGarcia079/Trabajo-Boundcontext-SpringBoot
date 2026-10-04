package com.mindconnect.infrastructure.professionalstudy.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import com.mindconnect.domain.professionalstudy.model.aggregate.ProfessionalStudy;
import com.mindconnect.domain.professionalstudy.model.valueobject.ProfessionalStudyId;
import com.mindconnect.domain.professionalstudy.port.repository.ProfessionalStudyRepository;
import com.mindconnect.infrastructure.professionalstudy.adapters.out.persistence.mappers.ProfessionalStudyPersistenceMapper;

/**
 * Adaptador: implementa el puerto del dominio usando Spring Data y el mapper.
 */
public class ProfessionalStudyRepositoryAdapter implements ProfessionalStudyRepository {

    private final ProfessionalStudyJpaRepository jpaRepository;
    private final ProfessionalStudyPersistenceMapper mapper;

    public ProfessionalStudyRepositoryAdapter(ProfessionalStudyJpaRepository jpaRepository, ProfessionalStudyPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public ProfessionalStudy save(ProfessionalStudy aggregate) {
        return mapper.toDomain(jpaRepository.save(mapper.toJpa(aggregate)));
    }

    @Override
    public Optional<ProfessionalStudy> findById(ProfessionalStudyId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    public List<ProfessionalStudy> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public void delete(ProfessionalStudy aggregate) {
        jpaRepository.deleteById(aggregate.id().value());
    }
}
