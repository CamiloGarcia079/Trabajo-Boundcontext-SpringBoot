package com.mindconnect.infrastructure.encountermodality.adapters.in.rest.controllers;

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

import com.mindconnect.application.encountermodality.command.RegisterEncounterModalityCommand;
import com.mindconnect.application.encountermodality.command.UpdateEncounterModalityCommand;
import com.mindconnect.application.encountermodality.dto.EncounterModalityResponse;
import com.mindconnect.application.encountermodality.usecase.DeleteEncounterModalityUseCase;
import com.mindconnect.application.encountermodality.usecase.GetEncounterModalityByIdUseCase;
import com.mindconnect.application.encountermodality.usecase.ListEncounterModalityUseCase;
import com.mindconnect.application.encountermodality.usecase.RegisterEncounterModalityUseCase;
import com.mindconnect.application.encountermodality.usecase.UpdateEncounterModalityUseCase;
import com.mindconnect.domain.encountermodality.model.valueobject.EncounterModalityId;
import com.mindconnect.infrastructure.encountermodality.adapters.in.rest.dtos.CreateEncounterModalityRequest;
import com.mindconnect.infrastructure.encountermodality.adapters.in.rest.dtos.UpdateEncounterModalityRequest;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/encounter-modalities")
public class EncounterModalityController {

    private final RegisterEncounterModalityUseCase registerUseCase;
    private final GetEncounterModalityByIdUseCase getByIdUseCase;
    private final ListEncounterModalityUseCase listUseCase;
    private final UpdateEncounterModalityUseCase updateUseCase;
    private final DeleteEncounterModalityUseCase deleteUseCase;

    public EncounterModalityController(
            RegisterEncounterModalityUseCase registerUseCase,
            GetEncounterModalityByIdUseCase getByIdUseCase,
            ListEncounterModalityUseCase listUseCase,
            UpdateEncounterModalityUseCase updateUseCase,
            DeleteEncounterModalityUseCase deleteUseCase
    ) {
        this.registerUseCase = registerUseCase;
        this.getByIdUseCase = getByIdUseCase;
        this.listUseCase = listUseCase;
        this.updateUseCase = updateUseCase;
        this.deleteUseCase = deleteUseCase;
    }

    @PostMapping
    public ResponseEntity<EncounterModalityResponse> create(@Valid @RequestBody CreateEncounterModalityRequest request) {
        var command = new RegisterEncounterModalityCommand(
                request.code(), request.name());
        return ResponseEntity.status(HttpStatus.CREATED).body(registerUseCase.execute(command));
    }

    @GetMapping
    public ResponseEntity<List<EncounterModalityResponse>> findAll() {
        return ResponseEntity.ok(listUseCase.execute());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EncounterModalityResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(getByIdUseCase.execute(new EncounterModalityId(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EncounterModalityResponse> update(@PathVariable UUID id, @Valid @RequestBody UpdateEncounterModalityRequest request) {
        var command = new UpdateEncounterModalityCommand(
                new EncounterModalityId(id),
                request.code(), request.name());
        return ResponseEntity.ok(updateUseCase.execute(command));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        deleteUseCase.execute(new EncounterModalityId(id));
        return ResponseEntity.noContent().build();
    }
}
