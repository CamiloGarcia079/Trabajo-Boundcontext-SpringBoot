package com.mindconnect.application.messagetype.usecase;

import com.mindconnect.application.messagetype.command.RegisterMessageTypeCommand;
import com.mindconnect.application.messagetype.dto.MessageTypeResponse;
import com.mindconnect.domain.messagetype.model.aggregate.MessageType;
import com.mindconnect.domain.messagetype.port.repository.MessageTypeRepository;

public class RegisterMessageTypeUseCase {

    private final MessageTypeRepository repository;

    public RegisterMessageTypeUseCase(MessageTypeRepository repository) {
        this.repository = repository;
    }

    public MessageTypeResponse execute(RegisterMessageTypeCommand command) {
        MessageType aggregate = MessageType.register(
                command.nameType());
        MessageType saved = repository.save(aggregate);
        return MessageTypeResponse.fromDomain(saved);
    }
}
