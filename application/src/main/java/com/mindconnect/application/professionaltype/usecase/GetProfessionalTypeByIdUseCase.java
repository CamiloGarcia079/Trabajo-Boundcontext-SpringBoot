package com.mindconnect.application.professionaltype.usecase;

import com.mindconnect.application.professionaltype.dto.ProfessionalTypeResponse;
import com.mindconnect.application.professionaltype.exception.ProfessionalTypeNotFoundApplicationException;
import com.mindconnect.domain.professionaltype.model.valueobject.ProfessionalTypeId;
import com.mindconnect.domain.professionaltype.port.repository.ProfessionalTypeRepository;

public class GetProfessionalTypeByIdUseCase {

    private final ProfessionalTypeRepository repository;

    public GetProfessionalTypeByIdUseCase(ProfessionalTypeRepository repository) {
        this.repository = repository;
    }

    public ProfessionalTypeResponse execute(ProfessionalTypeId id) {
        return repository.findById(id)
                .map(ProfessionalTypeResponse::fromDomain)
                .orElseThrow(() -> new ProfessionalTypeNotFoundApplicationException(id));
    }
}
