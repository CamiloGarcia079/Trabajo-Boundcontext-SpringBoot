package com.mindconnect.infrastructure.clinicalrecordstatus.adapters.out.persistence.mappers;

import com.mindconnect.domain.clinicalrecordstatus.model.aggregate.ClinicalRecordStatus;
import com.mindconnect.domain.clinicalrecordstatus.model.valueobject.ClinicalRecordStatusId;
import com.mindconnect.infrastructure.clinicalrecordstatus.adapters.out.persistence.entity.ClinicalRecordStatusJpaEntity;

/**
 * Convierte entre el agregado de dominio y la entidad JPA de clinical_record_statuses.
 */
public class ClinicalRecordStatusPersistenceMapper {

    public ClinicalRecordStatusJpaEntity toJpa(ClinicalRecordStatus domain) {
        if (domain == null) {
            return null;
        }

        ClinicalRecordStatusJpaEntity jpa = new ClinicalRecordStatusJpaEntity();
        jpa.setId(domain.id().value());
        jpa.setCode(domain.code());
        jpa.setName(domain.name());
        jpa.setCreatedAt(domain.createdAt());
        jpa.setUpdatedAt(domain.updatedAt());

        return jpa;
    }

    public ClinicalRecordStatus toDomain(ClinicalRecordStatusJpaEntity jpa) {
        if (jpa == null) {
            return null;
        }

        return ClinicalRecordStatus.restore(
                new ClinicalRecordStatusId(jpa.getId()),
                jpa.getCode(), jpa.getName(), jpa.getCreatedAt(), jpa.getUpdatedAt());
    }
}
