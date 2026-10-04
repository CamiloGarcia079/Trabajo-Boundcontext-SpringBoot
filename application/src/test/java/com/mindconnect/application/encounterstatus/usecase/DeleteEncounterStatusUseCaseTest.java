package com.mindconnect.application.encounterstatus.usecase;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import com.mindconnect.application.encounterstatus.exception.EncounterStatusNotFoundApplicationException;
import com.mindconnect.domain.encounterstatus.event.EncounterStatusDeletedEvent;
import com.mindconnect.domain.encounterstatus.model.aggregate.EncounterStatus;
import com.mindconnect.domain.encounterstatus.model.valueobject.EncounterStatusId;
import com.mindconnect.domain.encounterstatus.port.repository.EncounterStatusRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DeleteEncounterStatusUseCaseTest {

    @Test
    void eliminaUnRegistroExistente() {
        EncounterStatus aggregate = EncounterStatus.register("valor-a", "valor-a");
        FakeEncounterStatusRepository repository = new FakeEncounterStatusRepository();
        repository.save(aggregate);
        DeleteEncounterStatusUseCase useCase = new DeleteEncounterStatusUseCase(repository);

        EncounterStatusDeletedEvent event = useCase.execute(aggregate.id());

        assertSame(aggregate, repository.deleted);
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void rechazaEliminarUnRegistroQueNoExiste() {
        FakeEncounterStatusRepository repository = new FakeEncounterStatusRepository();
        DeleteEncounterStatusUseCase useCase = new DeleteEncounterStatusUseCase(repository);

        assertThrows(EncounterStatusNotFoundApplicationException.class, () -> useCase.execute(EncounterStatusId.generate()));
        assertNull(repository.deleted);
    }

    /** Repositorio falso en memoria: así se prueba el caso de uso sin base de datos. */
    private static final class FakeEncounterStatusRepository implements EncounterStatusRepository {
        private final Map<EncounterStatusId, EncounterStatus> store = new LinkedHashMap<>();
        private EncounterStatus deleted;

        @Override
        public EncounterStatus save(EncounterStatus aggregate) {
            store.put(aggregate.id(), aggregate);
            return aggregate;
        }

        @Override
        public Optional<EncounterStatus> findById(EncounterStatusId id) {
            return Optional.ofNullable(store.get(id));
        }

        @Override
        public List<EncounterStatus> findAll() {
            return new ArrayList<>(store.values());
        }

        @Override
        public void delete(EncounterStatus aggregate) {
            deleted = aggregate;
            store.remove(aggregate.id());
        }
    }
}
