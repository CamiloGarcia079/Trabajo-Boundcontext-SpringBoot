package com.mindconnect.infrastructure.mentalstatusexam.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import com.mindconnect.domain.mentalstatusexam.model.aggregate.MentalStatusExam;
import com.mindconnect.domain.mentalstatusexam.model.valueobject.MentalStatusExamId;
import com.mindconnect.domain.mentalstatusexam.port.repository.MentalStatusExamRepository;
import com.mindconnect.infrastructure.mentalstatusexam.adapters.out.persistence.mappers.MentalStatusExamPersistenceMapper;

/**
 * Adaptador: implementa el puerto del dominio usando Spring Data y el mapper.
 */
public class MentalStatusExamRepositoryAdapter implements MentalStatusExamRepository {

    private final MentalStatusExamJpaRepository jpaRepository;
    private final MentalStatusExamPersistenceMapper mapper;

    public MentalStatusExamRepositoryAdapter(MentalStatusExamJpaRepository jpaRepository, MentalStatusExamPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public MentalStatusExam save(MentalStatusExam aggregate) {
        return mapper.toDomain(jpaRepository.save(mapper.toJpa(aggregate)));
    }

    @Override
    public Optional<MentalStatusExam> findById(MentalStatusExamId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    public List<MentalStatusExam> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public void delete(MentalStatusExam aggregate) {
        jpaRepository.deleteById(aggregate.id().value());
    }
}
