package com.mindconnect.domain.mentalstatusexam.port.repository;

import java.util.List;
import java.util.Optional;

import com.mindconnect.domain.mentalstatusexam.model.aggregate.MentalStatusExam;
import com.mindconnect.domain.mentalstatusexam.model.valueobject.MentalStatusExamId;

/**
 * Puerto de salida: lo que el dominio necesita para guardar y buscar MentalStatusExam.
 * Lo implementa la infraestructura.
 */
public interface MentalStatusExamRepository {

    MentalStatusExam save(MentalStatusExam aggregate);

    Optional<MentalStatusExam> findById(MentalStatusExamId id);

    List<MentalStatusExam> findAll();

    void delete(MentalStatusExam aggregate);
}
