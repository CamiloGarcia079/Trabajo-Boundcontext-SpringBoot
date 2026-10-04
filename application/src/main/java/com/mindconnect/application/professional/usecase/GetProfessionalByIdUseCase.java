package com.mindconnect.application.professional.usecase;

import com.mindconnect.application.professional.dto.ProfessionalResponse;
import com.mindconnect.application.professional.exception.ProfessionalNotFoundApplicationException;
import com.mindconnect.domain.professional.model.valueobject.ProfessionalId;
import com.mindconnect.domain.professional.port.repository.ProfessionalRepository;

public class GetProfessionalByIdUseCase {

    private final ProfessionalRepository repository;

    public GetProfessionalByIdUseCase(ProfessionalRepository repository) {
        this.repository = repository;
    }

    public ProfessionalResponse execute(ProfessionalId id) {
        return repository.findById(id)
                .map(ProfessionalResponse::fromDomain)
                .orElseThrow(() -> new ProfessionalNotFoundApplicationException(id));
    }
}
