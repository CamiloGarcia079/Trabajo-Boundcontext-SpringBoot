package com.mindconnect.application.phonecontact.usecase;

import com.mindconnect.application.phonecontact.dto.PhoneContactResponse;
import com.mindconnect.application.phonecontact.exception.PhoneContactNotFoundApplicationException;
import com.mindconnect.domain.phonecontact.model.valueobject.PhoneContactId;
import com.mindconnect.domain.phonecontact.port.repository.PhoneContactRepository;

public class GetPhoneContactByIdUseCase {

    private final PhoneContactRepository repository;

    public GetPhoneContactByIdUseCase(PhoneContactRepository repository) {
        this.repository = repository;
    }

    public PhoneContactResponse execute(PhoneContactId id) {
        return repository.findById(id)
                .map(PhoneContactResponse::fromDomain)
                .orElseThrow(() -> new PhoneContactNotFoundApplicationException(id));
    }
}
