package com.mindconnect.application.chatparticipant.usecase;

import com.mindconnect.application.chatparticipant.command.UpdateChatParticipantCommand;
import com.mindconnect.application.chatparticipant.dto.ChatParticipantResponse;
import com.mindconnect.application.chatparticipant.exception.ChatParticipantNotFoundApplicationException;
import com.mindconnect.domain.chatparticipant.model.aggregate.ChatParticipant;
import com.mindconnect.domain.chatparticipant.port.repository.ChatParticipantRepository;

public class UpdateChatParticipantUseCase {

    private final ChatParticipantRepository repository;

    public UpdateChatParticipantUseCase(ChatParticipantRepository repository) {
        this.repository = repository;
    }

    public ChatParticipantResponse execute(UpdateChatParticipantCommand command) {
        ChatParticipant aggregate = repository.findById(command.id())
                .orElseThrow(() -> new ChatParticipantNotFoundApplicationException(command.id()));
        aggregate.update(
                command.conversationId(), command.participantTypeId(), command.patientId(), command.professionalId());
        ChatParticipant saved = repository.save(aggregate);
        return ChatParticipantResponse.fromDomain(saved);
    }
}
