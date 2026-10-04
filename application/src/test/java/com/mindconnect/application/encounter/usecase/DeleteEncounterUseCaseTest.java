package com.mindconnect.application.encounter.usecase;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import com.mindconnect.application.encounter.exception.EncounterNotFoundApplicationException;
import com.mindconnect.domain.encounter.event.EncounterDeletedEvent;
import com.mindconnect.domain.encounter.model.aggregate.Encounter;
import com.mindconnect.domain.encounter.model.valueobject.EncounterId;
import com.mindconnect.domain.encounter.port.repository.EncounterRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DeleteEncounterUseCaseTest {

    @Test
    void eliminaUnRegistroExistente() {
        Encounter aggregate = Encounter.register(UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID(), LocalDateTime.now(), LocalDateTime.now(), "valor-a", "valor-a", UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID());
        FakeEncounterRepository repository = new FakeEncounterRepository();
        repository.save(aggregate);
        DeleteEncounterUseCase useCase = new DeleteEncounterUseCase(repository);

        EncounterDeletedEvent event = useCase.execute(aggregate.id());

        assertSame(aggregate, repository.deleted);
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void rechazaEliminarUnRegistroQueNoExiste() {
        FakeEncounterRepository repository = new FakeEncounterRepository();
        DeleteEncounterUseCase useCase = new DeleteEncounterUseCase(repository);

        assertThrows(EncounterNotFoundApplicationException.class, () -> useCase.execute(EncounterId.generate()));
        assertNull(repository.deleted);
    }

    /** Repositorio falso en memoria: así se prueba el caso de uso sin base de datos. */
    private static final class FakeEncounterRepository implements EncounterRepository {
        private final Map<EncounterId, Encounter> store = new LinkedHashMap<>();
        private Encounter deleted;

        @Override
        public Encounter save(Encounter aggregate) {
            store.put(aggregate.id(), aggregate);
            return aggregate;
        }

        @Override
        public Optional<Encounter> findById(EncounterId id) {
            return Optional.ofNullable(store.get(id));
        }

        @Override
        public List<Encounter> findAll() {
            return new ArrayList<>(store.values());
        }

        @Override
        public void delete(Encounter aggregate) {
            deleted = aggregate;
            store.remove(aggregate.id());
        }
    }
}
