package com.mindconnect.domain.professionaltype.port.repository;

import java.util.List;
import java.util.Optional;

import com.mindconnect.domain.professionaltype.model.aggregate.ProfessionalType;
import com.mindconnect.domain.professionaltype.model.valueobject.ProfessionalTypeId;

/**
 * Puerto de salida: lo que el dominio necesita para guardar y buscar ProfessionalType.
 * Lo implementa la infraestructura.
 */
public interface ProfessionalTypeRepository {

    ProfessionalType save(ProfessionalType aggregate);

    Optional<ProfessionalType> findById(ProfessionalTypeId id);

    List<ProfessionalType> findAll();

    void delete(ProfessionalType aggregate);
}
