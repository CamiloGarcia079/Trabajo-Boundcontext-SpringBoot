package com.mindconnect.application.emailcontact.usecase;

import com.mindconnect.application.emailcontact.dto.EmailContactResponse;
import com.mindconnect.application.emailcontact.exception.EmailContactNotFoundApplicationException;
import com.mindconnect.domain.emailcontact.model.valueobject.EmailContactId;
import com.mindconnect.domain.emailcontact.port.repository.EmailContactRepository;

public class GetEmailContactByIdUseCase {

    private final EmailContactRepository repository;

    public GetEmailContactByIdUseCase(EmailContactRepository repository) {
        this.repository = repository;
    }

    public EmailContactResponse execute(EmailContactId id) {
        return repository.findById(id)
                .map(EmailContactResponse::fromDomain)
                .orElseThrow(() -> new EmailContactNotFoundApplicationException(id));
    }
}
