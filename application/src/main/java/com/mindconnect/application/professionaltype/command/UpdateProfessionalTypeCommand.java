package com.mindconnect.application.professionaltype.command;


import com.mindconnect.domain.professionaltype.model.valueobject.ProfessionalTypeId;

public record UpdateProfessionalTypeCommand(
        ProfessionalTypeId id,
        String name
) {
}
