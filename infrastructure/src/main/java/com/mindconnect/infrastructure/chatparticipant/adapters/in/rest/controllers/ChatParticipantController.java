package com.mindconnect.infrastructure.chatparticipant.adapters.in.rest.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mindconnect.application.chatparticipant.command.RegisterChatParticipantCommand;
import com.mindconnect.application.chatparticipant.command.UpdateChatParticipantCommand;
import com.mindconnect.application.chatparticipant.dto.ChatParticipantResponse;
import com.mindconnect.application.chatparticipant.usecase.DeleteChatParticipantUseCase;
import com.mindconnect.application.chatparticipant.usecase.GetChatParticipantByIdUseCase;
import com.mindconnect.application.chatparticipant.usecase.ListChatParticipantUseCase;
import com.mindconnect.application.chatparticipant.usecase.RegisterChatParticipantUseCase;
import com.mindconnect.application.chatparticipant.usecase.UpdateChatParticipantUseCase;
import com.mindconnect.domain.chatparticipant.model.valueobject.ChatParticipantId;
import com.mindconnect.infrastructure.chatparticipant.adapters.in.rest.dtos.CreateChatParticipantRequest;
import com.mindconnect.infrastructure.chatparticipant.adapters.in.rest.dtos.UpdateChatParticipantRequest;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/chat-participants")
public class ChatParticipantController {

    private final RegisterChatParticipantUseCase registerUseCase;
    private final GetChatParticipantByIdUseCase getByIdUseCase;
    private final ListChatParticipantUseCase listUseCase;
    private final UpdateChatParticipantUseCase updateUseCase;
    private final DeleteChatParticipantUseCase deleteUseCase;

    public ChatParticipantController(
            RegisterChatParticipantUseCase registerUseCase,
            GetChatParticipantByIdUseCase getByIdUseCase,
            ListChatParticipantUseCase listUseCase,
            UpdateChatParticipantUseCase updateUseCase,
            DeleteChatParticipantUseCase deleteUseCase
    ) {
        this.registerUseCase = registerUseCase;
        this.getByIdUseCase = getByIdUseCase;
        this.listUseCase = listUseCase;
        this.updateUseCase = updateUseCase;
        this.deleteUseCase = deleteUseCase;
    }

    @PostMapping
    public ResponseEntity<ChatParticipantResponse> create(@Valid @RequestBody CreateChatParticipantRequest request) {
        var command = new RegisterChatParticipantCommand(
                request.conversationId(), request.participantTypeId(), request.patientId(), request.professionalId());
        return ResponseEntity.status(HttpStatus.CREATED).body(registerUseCase.execute(command));
    }

    @GetMapping
    public ResponseEntity<List<ChatParticipantResponse>> findAll() {
        return ResponseEntity.ok(listUseCase.execute());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChatParticipantResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(getByIdUseCase.execute(new ChatParticipantId(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ChatParticipantResponse> update(@PathVariable UUID id, @Valid @RequestBody UpdateChatParticipantRequest request) {
        var command = new UpdateChatParticipantCommand(
                new ChatParticipantId(id),
                request.conversationId(), request.participantTypeId(), request.patientId(), request.professionalId());
        return ResponseEntity.ok(updateUseCase.execute(command));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        deleteUseCase.execute(new ChatParticipantId(id));
        return ResponseEntity.noContent().build();
    }
}
