package com.mindconnect.application.diagnosticsystem.usecase;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mindconnect.application.diagnosticsystem.command.RegisterDiagnosticSystemCommand;
import com.mindconnect.application.diagnosticsystem.command.UpdateDiagnosticSystemCommand;
import com.mindconnect.application.diagnosticsystem.dto.DiagnosticSystemResponse;
import com.mindconnect.application.diagnosticsystem.exception.DiagnosticSystemNotFoundApplicationException;
import com.mindconnect.domain.diagnosticsystem.model.aggregate.DiagnosticSystem;
import com.mindconnect.domain.diagnosticsystem.model.valueobject.DiagnosticSystemId;
import com.mindconnect.domain.diagnosticsystem.port.repository.DiagnosticSystemRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DiagnosticSystemUseCasesTest {

    private FakeDiagnosticSystemRepository repository;
    private RegisterDiagnosticSystemUseCase register;
    private GetDiagnosticSystemByIdUseCase getById;
    private ListDiagnosticSystemUseCase list;
    private UpdateDiagnosticSystemUseCase update;

    @BeforeEach
    void setUp() {
        repository = new FakeDiagnosticSystemRepository();
        register = new RegisterDiagnosticSystemUseCase(repository);
        getById = new GetDiagnosticSystemByIdUseCase(repository);
        list = new ListDiagnosticSystemUseCase(repository);
        update = new UpdateDiagnosticSystemUseCase(repository);
    }

    @Test
    void registrarGuardaYDevuelveElRegistroConIdGenerado() {
        DiagnosticSystemResponse created = register.execute(commandA());

        assertNotNull(created.id());
        assertEquals(1, repository.findAll().size());
    }

    @Test
    void buscarPorIdDevuelveLoGuardado() {
        DiagnosticSystemResponse created = register.execute(commandA());

        assertEquals(created, getById.execute(new DiagnosticSystemId(created.id())));
    }

    @Test
    void buscarUnIdInexistenteLanzaNotFound() {
        assertThrows(DiagnosticSystemNotFoundApplicationException.class, () -> getById.execute(DiagnosticSystemId.generate()));
    }

    @Test
    void listarDevuelveTodosLosRegistros() {
        register.execute(commandA());
        register.execute(commandA());

        assertEquals(2, list.execute().size());
    }

    @Test
    void actualizarConservaElId() {
        DiagnosticSystemResponse created = register.execute(commandA());

        DiagnosticSystemResponse updated = update.execute(updateCommand(new DiagnosticSystemId(created.id())));

        assertEquals(created.id(), updated.id());
        assertEquals(updated, getById.execute(new DiagnosticSystemId(created.id())));
    }

    @Test
    void actualizarUnIdInexistenteLanzaNotFound() {
        assertThrows(DiagnosticSystemNotFoundApplicationException.class, () -> update.execute(updateCommand(DiagnosticSystemId.generate())));
    }

    private RegisterDiagnosticSystemCommand commandA() {
        return new RegisterDiagnosticSystemCommand("valor-a", "valor-a", "valor-a");
    }

    private UpdateDiagnosticSystemCommand updateCommand(DiagnosticSystemId id) {
        return new UpdateDiagnosticSystemCommand(id, "valor-b", "valor-b", "valor-b");
    }

    /** Repositorio falso en memoria: así se prueba el caso de uso sin base de datos. */
    private static final class FakeDiagnosticSystemRepository implements DiagnosticSystemRepository {
        private final Map<DiagnosticSystemId, DiagnosticSystem> store = new LinkedHashMap<>();

        @Override
        public DiagnosticSystem save(DiagnosticSystem aggregate) {
            store.put(aggregate.id(), aggregate);
            return aggregate;
        }

        @Override
        public Optional<DiagnosticSystem> findById(DiagnosticSystemId id) {
            return Optional.ofNullable(store.get(id));
        }

        @Override
        public List<DiagnosticSystem> findAll() {
            return new ArrayList<>(store.values());
        }

        @Override
        public void delete(DiagnosticSystem aggregate) {
            store.remove(aggregate.id());
        }
    }
}
