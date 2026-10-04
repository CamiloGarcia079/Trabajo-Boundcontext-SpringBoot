package com.mindconnect.domain.professional.port.repository;

import java.util.List;
import java.util.Optional;

import com.mindconnect.domain.professional.model.aggregate.Professional;
import com.mindconnect.domain.professional.model.valueobject.ProfessionalId;

/**
 * Puerto de salida: lo que el dominio necesita para guardar y buscar Professional.
 * Lo implementa la infraestructura.
 */
public interface ProfessionalRepository {

    Professional save(Professional aggregate);

    Optional<Professional> findById(ProfessionalId id);

    List<Professional> findAll();

    void delete(Professional aggregate);
}
