package com.mindconnect.infrastructure.professionalstudy.adapters.out.persistence.mappers;

import com.mindconnect.domain.professionalstudy.model.aggregate.ProfessionalStudy;
import com.mindconnect.domain.professionalstudy.model.valueobject.ProfessionalStudyId;
import com.mindconnect.infrastructure.professionalstudy.adapters.out.persistence.entity.ProfessionalStudyJpaEntity;

/**
 * Convierte entre el agregado de dominio y la entidad JPA de professional_studies.
 */
public class ProfessionalStudyPersistenceMapper {

    public ProfessionalStudyJpaEntity toJpa(ProfessionalStudy domain) {
        if (domain == null) {
            return null;
        }

        ProfessionalStudyJpaEntity jpa = new ProfessionalStudyJpaEntity();
        jpa.setId(domain.id().value());
        jpa.setStudyId(domain.studyId());
        jpa.setProfessionalId(domain.professionalId());
        jpa.setTitle(domain.title());
        jpa.setUniversity(domain.university());
        jpa.setIsValid(domain.isValid());
        jpa.setResolutionNumber(domain.resolutionNumber());
        jpa.setCountryId(domain.countryId());
        jpa.setCreatedAt(domain.createdAt());
        jpa.setUpdatedAt(domain.updatedAt());

        return jpa;
    }

    public ProfessionalStudy toDomain(ProfessionalStudyJpaEntity jpa) {
        if (jpa == null) {
            return null;
        }

        return ProfessionalStudy.restore(
                new ProfessionalStudyId(jpa.getId()),
                jpa.getStudyId(), jpa.getProfessionalId(), jpa.getTitle(), jpa.getUniversity(), jpa.getIsValid(), jpa.getResolutionNumber(), jpa.getCountryId(), jpa.getCreatedAt(), jpa.getUpdatedAt());
    }
}
