package com.mindconnect.infrastructure.mentalstatusexam.adapters.out.persistence.mappers;

import com.mindconnect.domain.mentalstatusexam.model.aggregate.MentalStatusExam;
import com.mindconnect.domain.mentalstatusexam.model.valueobject.MentalStatusExamId;
import com.mindconnect.infrastructure.mentalstatusexam.adapters.out.persistence.entity.MentalStatusExamJpaEntity;

/**
 * Convierte entre el agregado de dominio y la entidad JPA de mental_status_exams.
 */
public class MentalStatusExamPersistenceMapper {

    public MentalStatusExamJpaEntity toJpa(MentalStatusExam domain) {
        if (domain == null) {
            return null;
        }

        MentalStatusExamJpaEntity jpa = new MentalStatusExamJpaEntity();
        jpa.setId(domain.id().value());
        jpa.setEncounterId(domain.encounterId());
        jpa.setAppearance(domain.appearance());
        jpa.setBehavior(domain.behavior());
        jpa.setAttitude(domain.attitude());
        jpa.setConsciousness(domain.consciousness());
        jpa.setOrientation(domain.orientation());
        jpa.setAttention(domain.attention());
        jpa.setMemory(domain.memory());
        jpa.setSpeech(domain.speech());
        jpa.setMood(domain.mood());
        jpa.setAffect(domain.affect());
        jpa.setThoughtProcess(domain.thoughtProcess());
        jpa.setThoughtContent(domain.thoughtContent());
        jpa.setPerception(domain.perception());
        jpa.setJudgment(domain.judgment());
        jpa.setInsight(domain.insight());
        jpa.setPsychomotorActivity(domain.psychomotorActivity());
        jpa.setObservations(domain.observations());
        jpa.setCreatedAt(domain.createdAt());
        jpa.setCreatedBy(domain.createdBy());

        return jpa;
    }

    public MentalStatusExam toDomain(MentalStatusExamJpaEntity jpa) {
        if (jpa == null) {
            return null;
        }

        return MentalStatusExam.restore(
                new MentalStatusExamId(jpa.getId()),
                jpa.getEncounterId(), jpa.getAppearance(), jpa.getBehavior(), jpa.getAttitude(), jpa.getConsciousness(), jpa.getOrientation(), jpa.getAttention(), jpa.getMemory(), jpa.getSpeech(), jpa.getMood(), jpa.getAffect(), jpa.getThoughtProcess(), jpa.getThoughtContent(), jpa.getPerception(), jpa.getJudgment(), jpa.getInsight(), jpa.getPsychomotorActivity(), jpa.getObservations(), jpa.getCreatedAt(), jpa.getCreatedBy());
    }
}
