package com.mindconnect.domain.clinicalrecordstatus.port.repository;

import java.util.List;
import java.util.Optional;

import com.mindconnect.domain.clinicalrecordstatus.model.aggregate.ClinicalRecordStatus;
import com.mindconnect.domain.clinicalrecordstatus.model.valueobject.ClinicalRecordStatusId;

/**
 * Puerto de salida: lo que el dominio necesita para guardar y buscar ClinicalRecordStatus.
 * Lo implementa la infraestructura.
 */
public interface ClinicalRecordStatusRepository {

    ClinicalRecordStatus save(ClinicalRecordStatus aggregate);

    Optional<ClinicalRecordStatus> findById(ClinicalRecordStatusId id);

    List<ClinicalRecordStatus> findAll();

    void delete(ClinicalRecordStatus aggregate);
}
