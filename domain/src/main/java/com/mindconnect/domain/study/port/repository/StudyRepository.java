package com.mindconnect.domain.study.port.repository;

import java.util.List;
import java.util.Optional;

import com.mindconnect.domain.study.model.aggregate.Study;
import com.mindconnect.domain.study.model.valueobject.StudyId;

/**
 * Puerto de salida: lo que el dominio necesita para guardar y buscar Study.
 * Lo implementa la infraestructura.
 */
public interface StudyRepository {

    Study save(Study aggregate);

    Optional<Study> findById(StudyId id);

    List<Study> findAll();

    void delete(Study aggregate);
}
