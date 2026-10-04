package com.mindconnect.domain.mentalstatusexam.exception;

import com.mindconnect.domain.common.exception.DomainException;
import com.mindconnect.domain.mentalstatusexam.model.valueobject.MentalStatusExamId;

public class MentalStatusExamNotFoundException extends DomainException {

    private static final long serialVersionUID = 1L;

    public MentalStatusExamNotFoundException(MentalStatusExamId id) {
        super("MentalStatusExam with id " + id.value() + " was not found.");
    }
}
