package com.mindconnect.application.gender.usecase;

import java.util.List;

import com.mindconnect.application.gender.dto.GenderResponse;
import com.mindconnect.domain.gender.port.repository.GenderRepository;

public class ListGenderUseCase {

    private final GenderRepository repository;

    public ListGenderUseCase(GenderRepository repository) {
        this.repository = repository;
    }

    public List<GenderResponse> execute() {
        return repository.findAll()
                .stream()
                .map(GenderResponse::fromDomain)
                .toList();
    }
}
