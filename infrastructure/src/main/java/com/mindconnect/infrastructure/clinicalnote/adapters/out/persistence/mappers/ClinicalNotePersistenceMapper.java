package com.mindconnect.infrastructure.clinicalnote.adapters.out.persistence.mappers;

import com.mindconnect.domain.clinicalnote.model.aggregate.ClinicalNote;
import com.mindconnect.domain.clinicalnote.model.valueobject.ClinicalNoteId;
import com.mindconnect.infrastructure.clinicalnote.adapters.out.persistence.entity.ClinicalNoteJpaEntity;

/**
 * Convierte entre el agregado de dominio y la entidad JPA de clinical_notes.
 */
public class ClinicalNotePersistenceMapper {

    public ClinicalNoteJpaEntity toJpa(ClinicalNote domain) {
        if (domain == null) {
            return null;
        }

        ClinicalNoteJpaEntity jpa = new ClinicalNoteJpaEntity();
        jpa.setId(domain.id().value());
        jpa.setEncounterId(domain.encounterId());
        jpa.setProfessionalId(domain.professionalId());
        jpa.setSubjective(domain.subjective());
        jpa.setObjective(domain.objective());
        jpa.setAssessment(domain.assessment());
        jpa.setPlan(domain.plan());
        jpa.setAdditionalNotes(domain.additionalNotes());
        jpa.setSignedAt(domain.signedAt());
        jpa.setCreatedAt(domain.createdAt());
        jpa.setUpdatedAt(domain.updatedAt());

        return jpa;
    }

    public ClinicalNote toDomain(ClinicalNoteJpaEntity jpa) {
        if (jpa == null) {
            return null;
        }

        return ClinicalNote.restore(
                new ClinicalNoteId(jpa.getId()),
                jpa.getEncounterId(), jpa.getProfessionalId(), jpa.getSubjective(), jpa.getObjective(), jpa.getAssessment(), jpa.getPlan(), jpa.getAdditionalNotes(), jpa.getSignedAt(), jpa.getCreatedAt(), jpa.getUpdatedAt());
    }
}
