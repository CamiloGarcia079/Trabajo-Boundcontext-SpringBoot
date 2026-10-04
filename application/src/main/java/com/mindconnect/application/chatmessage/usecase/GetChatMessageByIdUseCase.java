package com.mindconnect.application.chatmessage.usecase;

import com.mindconnect.application.chatmessage.dto.ChatMessageResponse;
import com.mindconnect.application.chatmessage.exception.ChatMessageNotFoundApplicationException;
import com.mindconnect.domain.chatmessage.model.valueobject.ChatMessageId;
import com.mindconnect.domain.chatmessage.port.repository.ChatMessageRepository;

public class GetChatMessageByIdUseCase {

    private final ChatMessageRepository repository;

    public GetChatMessageByIdUseCase(ChatMessageRepository repository) {
        this.repository = repository;
    }

    public ChatMessageResponse execute(ChatMessageId id) {
        return repository.findById(id)
                .map(ChatMessageResponse::fromDomain)
                .orElseThrow(() -> new ChatMessageNotFoundApplicationException(id));
    }
}
