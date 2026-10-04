package com.mindconnect.application.professionaltype.usecase;

import java.util.List;

import com.mindconnect.application.professionaltype.dto.ProfessionalTypeResponse;
import com.mindconnect.domain.professionaltype.port.repository.ProfessionalTypeRepository;

public class ListProfessionalTypeUseCase {

    private final ProfessionalTypeRepository repository;

    public ListProfessionalTypeUseCase(ProfessionalTypeRepository repository) {
        this.repository = repository;
    }

    public List<ProfessionalTypeResponse> execute() {
        return repository.findAll()
                .stream()
                .map(ProfessionalTypeResponse::fromDomain)
                .toList();
    }
}
