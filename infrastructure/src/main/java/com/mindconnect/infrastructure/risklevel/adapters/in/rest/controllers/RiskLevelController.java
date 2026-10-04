package com.mindconnect.infrastructure.risklevel.adapters.in.rest.controllers;

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

import com.mindconnect.application.risklevel.command.RegisterRiskLevelCommand;
import com.mindconnect.application.risklevel.command.UpdateRiskLevelCommand;
import com.mindconnect.application.risklevel.dto.RiskLevelResponse;
import com.mindconnect.application.risklevel.usecase.DeleteRiskLevelUseCase;
import com.mindconnect.application.risklevel.usecase.GetRiskLevelByIdUseCase;
import com.mindconnect.application.risklevel.usecase.ListRiskLevelUseCase;
import com.mindconnect.application.risklevel.usecase.RegisterRiskLevelUseCase;
import com.mindconnect.application.risklevel.usecase.UpdateRiskLevelUseCase;
import com.mindconnect.domain.risklevel.model.valueobject.RiskLevelId;
import com.mindconnect.infrastructure.risklevel.adapters.in.rest.dtos.CreateRiskLevelRequest;
import com.mindconnect.infrastructure.risklevel.adapters.in.rest.dtos.UpdateRiskLevelRequest;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/risk-levels")
public class RiskLevelController {

    private final RegisterRiskLevelUseCase registerUseCase;
    private final GetRiskLevelByIdUseCase getByIdUseCase;
    private final ListRiskLevelUseCase listUseCase;
    private final UpdateRiskLevelUseCase updateUseCase;
    private final DeleteRiskLevelUseCase deleteUseCase;

    public RiskLevelController(
            RegisterRiskLevelUseCase registerUseCase,
            GetRiskLevelByIdUseCase getByIdUseCase,
            ListRiskLevelUseCase listUseCase,
            UpdateRiskLevelUseCase updateUseCase,
            DeleteRiskLevelUseCase deleteUseCase
    ) {
        this.registerUseCase = registerUseCase;
        this.getByIdUseCase = getByIdUseCase;
        this.listUseCase = listUseCase;
        this.updateUseCase = updateUseCase;
        this.deleteUseCase = deleteUseCase;
    }

    @PostMapping
    public ResponseEntity<RiskLevelResponse> create(@Valid @RequestBody CreateRiskLevelRequest request) {
        var command = new RegisterRiskLevelCommand(
                request.code(), request.name(), request.severity());
        return ResponseEntity.status(HttpStatus.CREATED).body(registerUseCase.execute(command));
    }

    @GetMapping
    public ResponseEntity<List<RiskLevelResponse>> findAll() {
        return ResponseEntity.ok(listUseCase.execute());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RiskLevelResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(getByIdUseCase.execute(new RiskLevelId(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RiskLevelResponse> update(@PathVariable UUID id, @Valid @RequestBody UpdateRiskLevelRequest request) {
        var command = new UpdateRiskLevelCommand(
                new RiskLevelId(id),
                request.code(), request.name(), request.severity());
        return ResponseEntity.ok(updateUseCase.execute(command));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        deleteUseCase.execute(new RiskLevelId(id));
        return ResponseEntity.noContent().build();
    }
}
