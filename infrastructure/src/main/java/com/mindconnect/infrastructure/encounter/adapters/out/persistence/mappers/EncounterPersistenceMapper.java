package com.mindconnect.infrastructure.encounter.adapters.out.persistence.mappers;

import com.mindconnect.domain.encounter.model.aggregate.Encounter;
import com.mindconnect.domain.encounter.model.valueobject.EncounterId;
import com.mindconnect.infrastructure.encounter.adapters.out.persistence.entity.EncounterJpaEntity;

/**
 * Convierte entre el agregado de dominio y la entidad JPA de encounters.
 */
public class EncounterPersistenceMapper {

    public EncounterJpaEntity toJpa(Encounter domain) {
        if (domain == null) {
            return null;
        }

        EncounterJpaEntity jpa = new EncounterJpaEntity();
        jpa.setId(domain.id().value());
        jpa.setClinicalRecordId(domain.clinicalRecordId());
        jpa.setProfessionalId(domain.professionalId());
        jpa.setEncounterTypeId(domain.encounterTypeId());
        jpa.setStartedAt(domain.startedAt());
        jpa.setEndedAt(domain.endedAt());
        jpa.setReasonForVisit(domain.reasonForVisit());
        jpa.setCurrentCondition(domain.currentCondition());
        jpa.setModalityId(domain.modalityId());
        jpa.setStatusId(domain.statusId());
        jpa.setCreatedAt(domain.createdAt());
        jpa.setCreatedBy(domain.createdBy());
        jpa.setUpdatedAt(domain.updatedAt());
        jpa.setUpdatedBy(domain.updatedBy());

        return jpa;
    }

    public Encounter toDomain(EncounterJpaEntity jpa) {
        if (jpa == null) {
            return null;
        }

        return Encounter.restore(
                new EncounterId(jpa.getId()),
                jpa.getClinicalRecordId(), jpa.getProfessionalId(), jpa.getEncounterTypeId(), jpa.getStartedAt(), jpa.getEndedAt(), jpa.getReasonForVisit(), jpa.getCurrentCondition(), jpa.getModalityId(), jpa.getStatusId(), jpa.getCreatedAt(), jpa.getCreatedBy(), jpa.getUpdatedAt(), jpa.getUpdatedBy());
    }
}
