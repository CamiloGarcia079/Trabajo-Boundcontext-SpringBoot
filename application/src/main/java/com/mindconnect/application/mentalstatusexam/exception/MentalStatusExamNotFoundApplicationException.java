package com.mindconnect.application.mentalstatusexam.exception;

import com.mindconnect.application.common.exception.ApplicationException;
import com.mindconnect.domain.mentalstatusexam.model.valueobject.MentalStatusExamId;

public class MentalStatusExamNotFoundApplicationException extends ApplicationException {

    private static final long serialVersionUID = 1L;

    public MentalStatusExamNotFoundApplicationException(MentalStatusExamId id) {
        super("MentalStatusExam with id " + id.value() + " was not found.");
    }
}
