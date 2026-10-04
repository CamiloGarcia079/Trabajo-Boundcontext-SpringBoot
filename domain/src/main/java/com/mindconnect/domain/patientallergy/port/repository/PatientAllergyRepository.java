package com.mindconnect.domain.patientallergy.port.repository;

import java.util.List;
import java.util.Optional;

import com.mindconnect.domain.patientallergy.model.aggregate.PatientAllergy;
import com.mindconnect.domain.patientallergy.model.valueobject.PatientAllergyId;

/**
 * Puerto de salida: lo que el dominio necesita para guardar y buscar PatientAllergy.
 * Lo implementa la infraestructura.
 */
public interface PatientAllergyRepository {

    PatientAllergy save(PatientAllergy aggregate);

    Optional<PatientAllergy> findById(PatientAllergyId id);

    List<PatientAllergy> findAll();

    void delete(PatientAllergy aggregate);
}
