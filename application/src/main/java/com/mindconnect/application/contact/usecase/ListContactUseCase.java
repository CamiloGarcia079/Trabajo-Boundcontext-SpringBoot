package com.mindconnect.application.contact.usecase;

import java.util.List;

import com.mindconnect.application.contact.dto.ContactResponse;
import com.mindconnect.domain.contact.port.repository.ContactRepository;

public class ListContactUseCase {

    private final ContactRepository repository;

    public ListContactUseCase(ContactRepository repository) {
        this.repository = repository;
    }

    public List<ContactResponse> execute() {
        return repository.findAll()
                .stream()
                .map(ContactResponse::fromDomain)
                .toList();
    }
}
