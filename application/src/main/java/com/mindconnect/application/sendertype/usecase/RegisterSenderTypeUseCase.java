package com.mindconnect.application.sendertype.usecase;

import com.mindconnect.application.sendertype.command.RegisterSenderTypeCommand;
import com.mindconnect.application.sendertype.dto.SenderTypeResponse;
import com.mindconnect.domain.sendertype.model.aggregate.SenderType;
import com.mindconnect.domain.sendertype.port.repository.SenderTypeRepository;

public class RegisterSenderTypeUseCase {

    private final SenderTypeRepository repository;

    public RegisterSenderTypeUseCase(SenderTypeRepository repository) {
        this.repository = repository;
    }

    public SenderTypeResponse execute(RegisterSenderTypeCommand command) {
        SenderType aggregate = SenderType.register(
                command.nameType());
        SenderType saved = repository.save(aggregate);
        return SenderTypeResponse.fromDomain(saved);
    }
}
