package com.mindconnect.application.diagnosticsystem.usecase;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import com.mindconnect.application.diagnosticsystem.exception.DiagnosticSystemNotFoundApplicationException;
import com.mindconnect.domain.diagnosticsystem.event.DiagnosticSystemDeletedEvent;
import com.mindconnect.domain.diagnosticsystem.model.aggregate.DiagnosticSystem;
import com.mindconnect.domain.diagnosticsystem.model.valueobject.DiagnosticSystemId;
import com.mindconnect.domain.diagnosticsystem.port.repository.DiagnosticSystemRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DeleteDiagnosticSystemUseCaseTest {

    @Test
    void eliminaUnRegistroExistente() {
        DiagnosticSystem aggregate = DiagnosticSystem.register("valor-a", "valor-a", "valor-a");
        FakeDiagnosticSystemRepository repository = new FakeDiagnosticSystemRepository();
        repository.save(aggregate);
        DeleteDiagnosticSystemUseCase useCase = new DeleteDiagnosticSystemUseCase(repository);

        DiagnosticSystemDeletedEvent event = useCase.execute(aggregate.id());

        assertSame(aggregate, repository.deleted);
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void rechazaEliminarUnRegistroQueNoExiste() {
        FakeDiagnosticSystemRepository repository = new FakeDiagnosticSystemRepository();
        DeleteDiagnosticSystemUseCase useCase = new DeleteDiagnosticSystemUseCase(repository);

        assertThrows(DiagnosticSystemNotFoundApplicationException.class, () -> useCase.execute(DiagnosticSystemId.generate()));
        assertNull(repository.deleted);
    }

    /** Repositorio falso en memoria: así se prueba el caso de uso sin base de datos. */
    private static final class FakeDiagnosticSystemRepository implements DiagnosticSystemRepository {
        private final Map<DiagnosticSystemId, DiagnosticSystem> store = new LinkedHashMap<>();
        private DiagnosticSystem deleted;

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
            deleted = aggregate;
            store.remove(aggregate.id());
        }
    }
}
