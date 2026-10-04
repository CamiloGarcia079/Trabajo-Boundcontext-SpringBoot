package com.mindconnect.infrastructure.providermodelai.adapters.in.rest.controllers;

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

import com.mindconnect.application.providermodelai.command.RegisterProviderModelAiCommand;
import com.mindconnect.application.providermodelai.command.UpdateProviderModelAiCommand;
import com.mindconnect.application.providermodelai.dto.ProviderModelAiResponse;
import com.mindconnect.application.providermodelai.usecase.DeleteProviderModelAiUseCase;
import com.mindconnect.application.providermodelai.usecase.GetProviderModelAiByIdUseCase;
import com.mindconnect.application.providermodelai.usecase.ListProviderModelAiUseCase;
import com.mindconnect.application.providermodelai.usecase.RegisterProviderModelAiUseCase;
import com.mindconnect.application.providermodelai.usecase.UpdateProviderModelAiUseCase;
import com.mindconnect.domain.providermodelai.model.valueobject.ProviderModelAiId;
import com.mindconnect.infrastructure.providermodelai.adapters.in.rest.dtos.CreateProviderModelAiRequest;
import com.mindconnect.infrastructure.providermodelai.adapters.in.rest.dtos.UpdateProviderModelAiRequest;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/provider-models-ai")
public class ProviderModelAiController {

    private final RegisterProviderModelAiUseCase registerUseCase;
    private final GetProviderModelAiByIdUseCase getByIdUseCase;
    private final ListProviderModelAiUseCase listUseCase;
    private final UpdateProviderModelAiUseCase updateUseCase;
    private final DeleteProviderModelAiUseCase deleteUseCase;

    public ProviderModelAiController(
            RegisterProviderModelAiUseCase registerUseCase,
            GetProviderModelAiByIdUseCase getByIdUseCase,
            ListProviderModelAiUseCase listUseCase,
            UpdateProviderModelAiUseCase updateUseCase,
            DeleteProviderModelAiUseCase deleteUseCase
    ) {
        this.registerUseCase = registerUseCase;
        this.getByIdUseCase = getByIdUseCase;
        this.listUseCase = listUseCase;
        this.updateUseCase = updateUseCase;
        this.deleteUseCase = deleteUseCase;
    }

    @PostMapping
    public ResponseEntity<ProviderModelAiResponse> create(@Valid @RequestBody CreateProviderModelAiRequest request) {
        var command = new RegisterProviderModelAiCommand(
                request.nameProviderAi(), request.razonSocial(), request.sitioWeb());
        return ResponseEntity.status(HttpStatus.CREATED).body(registerUseCase.execute(command));
    }

    @GetMapping
    public ResponseEntity<List<ProviderModelAiResponse>> findAll() {
        return ResponseEntity.ok(listUseCase.execute());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProviderModelAiResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(getByIdUseCase.execute(new ProviderModelAiId(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProviderModelAiResponse> update(@PathVariable UUID id, @Valid @RequestBody UpdateProviderModelAiRequest request) {
        var command = new UpdateProviderModelAiCommand(
                new ProviderModelAiId(id),
                request.nameProviderAi(), request.razonSocial(), request.sitioWeb());
        return ResponseEntity.ok(updateUseCase.execute(command));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        deleteUseCase.execute(new ProviderModelAiId(id));
        return ResponseEntity.noContent().build();
    }
}
