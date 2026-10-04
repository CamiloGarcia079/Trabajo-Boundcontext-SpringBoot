package com.mindconnect.application.phonecontact.usecase;

import com.mindconnect.application.phonecontact.command.RegisterPhoneContactCommand;
import com.mindconnect.application.phonecontact.dto.PhoneContactResponse;
import com.mindconnect.domain.phonecontact.model.aggregate.PhoneContact;
import com.mindconnect.domain.phonecontact.port.repository.PhoneContactRepository;

public class RegisterPhoneContactUseCase {

    private final PhoneContactRepository repository;

    public RegisterPhoneContactUseCase(PhoneContactRepository repository) {
        this.repository = repository;
    }

    public PhoneContactResponse execute(RegisterPhoneContactCommand command) {
        PhoneContact aggregate = PhoneContact.register(
                command.contactId(), command.phone(), command.notes());
        PhoneContact saved = repository.save(aggregate);
        return PhoneContactResponse.fromDomain(saved);
    }
}
