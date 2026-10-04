package com.mindconnect.application.messagetype.usecase;

import com.mindconnect.application.messagetype.command.UpdateMessageTypeCommand;
import com.mindconnect.application.messagetype.dto.MessageTypeResponse;
import com.mindconnect.application.messagetype.exception.MessageTypeNotFoundApplicationException;
import com.mindconnect.domain.messagetype.model.aggregate.MessageType;
import com.mindconnect.domain.messagetype.port.repository.MessageTypeRepository;

public class UpdateMessageTypeUseCase {

    private final MessageTypeRepository repository;

    public UpdateMessageTypeUseCase(MessageTypeRepository repository) {
        this.repository = repository;
    }

    public MessageTypeResponse execute(UpdateMessageTypeCommand command) {
        MessageType aggregate = repository.findById(command.id())
                .orElseThrow(() -> new MessageTypeNotFoundApplicationException(command.id()));
        aggregate.update(
                command.nameType());
        MessageType saved = repository.save(aggregate);
        return MessageTypeResponse.fromDomain(saved);
    }
}
