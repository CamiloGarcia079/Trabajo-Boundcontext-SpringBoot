package com.mindconnect.application.escalationstatus.usecase;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import com.mindconnect.application.escalationstatus.exception.EscalationStatusNotFoundApplicationException;
import com.mindconnect.domain.escalationstatus.event.EscalationStatusDeletedEvent;
import com.mindconnect.domain.escalationstatus.model.aggregate.EscalationStatus;
import com.mindconnect.domain.escalationstatus.model.valueobject.EscalationStatusId;
import com.mindconnect.domain.escalationstatus.port.repository.EscalationStatusRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DeleteEscalationStatusUseCaseTest {

    @Test
    void eliminaUnRegistroExistente() {
        EscalationStatus aggregate = EscalationStatus.register("valor-a");
        FakeEscalationStatusRepository repository = new FakeEscalationStatusRepository();
        repository.save(aggregate);
        DeleteEscalationStatusUseCase useCase = new DeleteEscalationStatusUseCase(repository);

        EscalationStatusDeletedEvent event = useCase.execute(aggregate.id());

        assertSame(aggregate, repository.deleted);
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void rechazaEliminarUnRegistroQueNoExiste() {
        FakeEscalationStatusRepository repository = new FakeEscalationStatusRepository();
        DeleteEscalationStatusUseCase useCase = new DeleteEscalationStatusUseCase(repository);

        assertThrows(EscalationStatusNotFoundApplicationException.class, () -> useCase.execute(EscalationStatusId.generate()));
        assertNull(repository.deleted);
    }

    /** Repositorio falso en memoria: así se prueba el caso de uso sin base de datos. */
    private static final class FakeEscalationStatusRepository implements EscalationStatusRepository {
        private final Map<EscalationStatusId, EscalationStatus> store = new LinkedHashMap<>();
        private EscalationStatus deleted;

        @Override
        public EscalationStatus save(EscalationStatus aggregate) {
            store.put(aggregate.id(), aggregate);
            return aggregate;
        }

        @Override
        public Optional<EscalationStatus> findById(EscalationStatusId id) {
            return Optional.ofNullable(store.get(id));
        }

        @Override
        public List<EscalationStatus> findAll() {
            return new ArrayList<>(store.values());
        }

        @Override
        public void delete(EscalationStatus aggregate) {
            deleted = aggregate;
            store.remove(aggregate.id());
        }
    }
}
