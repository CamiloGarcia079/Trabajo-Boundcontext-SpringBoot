package com.mindconnect.application.professionalstudy.usecase;

import com.mindconnect.application.professionalstudy.dto.ProfessionalStudyResponse;
import com.mindconnect.application.professionalstudy.exception.ProfessionalStudyNotFoundApplicationException;
import com.mindconnect.domain.professionalstudy.model.valueobject.ProfessionalStudyId;
import com.mindconnect.domain.professionalstudy.port.repository.ProfessionalStudyRepository;

public class GetProfessionalStudyByIdUseCase {

    private final ProfessionalStudyRepository repository;

    public GetProfessionalStudyByIdUseCase(ProfessionalStudyRepository repository) {
        this.repository = repository;
    }

    public ProfessionalStudyResponse execute(ProfessionalStudyId id) {
        return repository.findById(id)
                .map(ProfessionalStudyResponse::fromDomain)
                .orElseThrow(() -> new ProfessionalStudyNotFoundApplicationException(id));
    }
}
