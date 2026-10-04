package com.mindconnect.infrastructure.clinicalrecord.adapters.in.rest.controllers;

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

import com.mindconnect.application.clinicalrecord.command.RegisterClinicalRecordCommand;
import com.mindconnect.application.clinicalrecord.command.UpdateClinicalRecordCommand;
import com.mindconnect.application.clinicalrecord.dto.ClinicalRecordResponse;
import com.mindconnect.application.clinicalrecord.usecase.DeleteClinicalRecordUseCase;
import com.mindconnect.application.clinicalrecord.usecase.GetClinicalRecordByIdUseCase;
import com.mindconnect.application.clinicalrecord.usecase.ListClinicalRecordUseCase;
import com.mindconnect.application.clinicalrecord.usecase.RegisterClinicalRecordUseCase;
import com.mindconnect.application.clinicalrecord.usecase.UpdateClinicalRecordUseCase;
import com.mindconnect.domain.clinicalrecord.model.valueobject.ClinicalRecordId;
import com.mindconnect.infrastructure.clinicalrecord.adapters.in.rest.dtos.CreateClinicalRecordRequest;
import com.mindconnect.infrastructure.clinicalrecord.adapters.in.rest.dtos.UpdateClinicalRecordRequest;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/clinical-records")
public class ClinicalRecordController {

    private final RegisterClinicalRecordUseCase registerUseCase;
    private final GetClinicalRecordByIdUseCase getByIdUseCase;
    private final ListClinicalRecordUseCase listUseCase;
    private final UpdateClinicalRecordUseCase updateUseCase;
    private final DeleteClinicalRecordUseCase deleteUseCase;

    public ClinicalRecordController(
            RegisterClinicalRecordUseCase registerUseCase,
            GetClinicalRecordByIdUseCase getByIdUseCase,
            ListClinicalRecordUseCase listUseCase,
            UpdateClinicalRecordUseCase updateUseCase,
            DeleteClinicalRecordUseCase deleteUseCase
    ) {
        this.registerUseCase = registerUseCase;
        this.getByIdUseCase = getByIdUseCase;
        this.listUseCase = listUseCase;
        this.updateUseCase = updateUseCase;
        this.deleteUseCase = deleteUseCase;
    }

    @PostMapping
    public ResponseEntity<ClinicalRecordResponse> create(@Valid @RequestBody CreateClinicalRecordRequest request) {
        var command = new RegisterClinicalRecordCommand(
                request.patientId(), request.creationDate(), request.recordNumber(), request.openedAt(), request.closedAt(), request.statusId(), request.createdBy());
        return ResponseEntity.status(HttpStatus.CREATED).body(registerUseCase.execute(command));
    }

    @GetMapping
    public ResponseEntity<List<ClinicalRecordResponse>> findAll() {
        return ResponseEntity.ok(listUseCase.execute());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClinicalRecordResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(getByIdUseCase.execute(new ClinicalRecordId(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClinicalRecordResponse> update(@PathVariable UUID id, @Valid @RequestBody UpdateClinicalRecordRequest request) {
        var command = new UpdateClinicalRecordCommand(
                new ClinicalRecordId(id),
                request.patientId(), request.creationDate(), request.recordNumber(), request.openedAt(), request.closedAt(), request.statusId());
        return ResponseEntity.ok(updateUseCase.execute(command));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        deleteUseCase.execute(new ClinicalRecordId(id));
        return ResponseEntity.noContent().build();
    }
}
