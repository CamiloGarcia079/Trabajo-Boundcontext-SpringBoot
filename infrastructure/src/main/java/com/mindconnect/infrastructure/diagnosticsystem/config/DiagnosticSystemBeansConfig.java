package com.mindconnect.infrastructure.diagnosticsystem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mindconnect.application.diagnosticsystem.usecase.DeleteDiagnosticSystemUseCase;
import com.mindconnect.application.diagnosticsystem.usecase.GetDiagnosticSystemByIdUseCase;
import com.mindconnect.application.diagnosticsystem.usecase.ListDiagnosticSystemUseCase;
import com.mindconnect.application.diagnosticsystem.usecase.RegisterDiagnosticSystemUseCase;
import com.mindconnect.application.diagnosticsystem.usecase.UpdateDiagnosticSystemUseCase;
import com.mindconnect.domain.diagnosticsystem.port.repository.DiagnosticSystemRepository;
import com.mindconnect.infrastructure.diagnosticsystem.adapters.out.persistence.mappers.DiagnosticSystemPersistenceMapper;
import com.mindconnect.infrastructure.diagnosticsystem.adapters.out.persistence.repositories.DiagnosticSystemJpaRepository;
import com.mindconnect.infrastructure.diagnosticsystem.adapters.out.persistence.repositories.DiagnosticSystemRepositoryAdapter;

/**
 * Conecta las piezas del contexto diagnosticsystem: mapper, repositorio y casos de uso.
 * Los casos de uso no usan Spring; aquí se crean y se les entrega lo que necesitan.
 */
@Configuration
public class DiagnosticSystemBeansConfig {

    @Bean
    public DiagnosticSystemPersistenceMapper diagnosticSystemPersistenceMapper() {
        return new DiagnosticSystemPersistenceMapper();
    }

    @Bean
    public DiagnosticSystemRepository diagnosticSystemRepository(DiagnosticSystemJpaRepository repository, DiagnosticSystemPersistenceMapper mapper) {
        return new DiagnosticSystemRepositoryAdapter(repository, mapper);
    }

    @Bean
    public RegisterDiagnosticSystemUseCase registerDiagnosticSystemUseCase(DiagnosticSystemRepository repository) {
        return new RegisterDiagnosticSystemUseCase(repository);
    }

    @Bean
    public GetDiagnosticSystemByIdUseCase getDiagnosticSystemByIdUseCase(DiagnosticSystemRepository repository) {
        return new GetDiagnosticSystemByIdUseCase(repository);
    }

    @Bean
    public ListDiagnosticSystemUseCase listDiagnosticSystemUseCase(DiagnosticSystemRepository repository) {
        return new ListDiagnosticSystemUseCase(repository);
    }

    @Bean
    public UpdateDiagnosticSystemUseCase updateDiagnosticSystemUseCase(DiagnosticSystemRepository repository) {
        return new UpdateDiagnosticSystemUseCase(repository);
    }

    @Bean
    public DeleteDiagnosticSystemUseCase deleteDiagnosticSystemUseCase(DiagnosticSystemRepository repository) {
        return new DeleteDiagnosticSystemUseCase(repository);
    }
}
