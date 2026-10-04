package com.mindconnect.domain.professionalstudy.port.repository;

import java.util.List;
import java.util.Optional;

import com.mindconnect.domain.professionalstudy.model.aggregate.ProfessionalStudy;
import com.mindconnect.domain.professionalstudy.model.valueobject.ProfessionalStudyId;

/**
 * Puerto de salida: lo que el dominio necesita para guardar y buscar ProfessionalStudy.
 * Lo implementa la infraestructura.
 */
public interface ProfessionalStudyRepository {

    ProfessionalStudy save(ProfessionalStudy aggregate);

    Optional<ProfessionalStudy> findById(ProfessionalStudyId id);

    List<ProfessionalStudy> findAll();

    void delete(ProfessionalStudy aggregate);
}
