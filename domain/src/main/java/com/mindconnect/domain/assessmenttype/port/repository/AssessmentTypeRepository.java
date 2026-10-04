package com.mindconnect.domain.assessmenttype.port.repository;

import java.util.List;
import java.util.Optional;

import com.mindconnect.domain.assessmenttype.model.aggregate.AssessmentType;
import com.mindconnect.domain.assessmenttype.model.valueobject.AssessmentTypeId;

/**
 * Puerto de salida: lo que el dominio necesita para guardar y buscar AssessmentType.
 * Lo implementa la infraestructura.
 */
public interface AssessmentTypeRepository {

    AssessmentType save(AssessmentType aggregate);

    Optional<AssessmentType> findById(AssessmentTypeId id);

    List<AssessmentType> findAll();

    void delete(AssessmentType aggregate);
}
