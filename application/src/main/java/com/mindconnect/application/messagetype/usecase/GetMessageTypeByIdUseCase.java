package com.mindconnect.application.messagetype.usecase;

import com.mindconnect.application.messagetype.dto.MessageTypeResponse;
import com.mindconnect.application.messagetype.exception.MessageTypeNotFoundApplicationException;
import com.mindconnect.domain.messagetype.model.valueobject.MessageTypeId;
import com.mindconnect.domain.messagetype.port.repository.MessageTypeRepository;

public class GetMessageTypeByIdUseCase {

    private final MessageTypeRepository repository;

    public GetMessageTypeByIdUseCase(MessageTypeRepository repository) {
        this.repository = repository;
    }

    public MessageTypeResponse execute(MessageTypeId id) {
        return repository.findById(id)
                .map(MessageTypeResponse::fromDomain)
                .orElseThrow(() -> new MessageTypeNotFoundApplicationException(id));
    }
}
