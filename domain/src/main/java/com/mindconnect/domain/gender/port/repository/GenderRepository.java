package com.mindconnect.domain.gender.port.repository;

import java.util.List;
import java.util.Optional;

import com.mindconnect.domain.gender.model.aggregate.Gender;
import com.mindconnect.domain.gender.model.valueobject.GenderId;

/**
 * Puerto de salida: lo que el dominio necesita para guardar y buscar Gender.
 * Lo implementa la infraestructura.
 */
public interface GenderRepository {

    Gender save(Gender aggregate);

    Optional<Gender> findById(GenderId id);

    List<Gender> findAll();

    void delete(Gender aggregate);
}
