package com.mindconnect.application.sendertype.usecase;

import com.mindconnect.application.sendertype.dto.SenderTypeResponse;
import com.mindconnect.application.sendertype.exception.SenderTypeNotFoundApplicationException;
import com.mindconnect.domain.sendertype.model.valueobject.SenderTypeId;
import com.mindconnect.domain.sendertype.port.repository.SenderTypeRepository;

public class GetSenderTypeByIdUseCase {

    private final SenderTypeRepository repository;

    public GetSenderTypeByIdUseCase(SenderTypeRepository repository) {
        this.repository = repository;
    }

    public SenderTypeResponse execute(SenderTypeId id) {
        return repository.findById(id)
                .map(SenderTypeResponse::fromDomain)
                .orElseThrow(() -> new SenderTypeNotFoundApplicationException(id));
    }
}
