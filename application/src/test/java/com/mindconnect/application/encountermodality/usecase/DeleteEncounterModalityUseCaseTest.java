package com.mindconnect.application.encountermodality.usecase;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import com.mindconnect.application.encountermodality.exception.EncounterModalityNotFoundApplicationException;
import com.mindconnect.domain.encountermodality.event.EncounterModalityDeletedEvent;
import com.mindconnect.domain.encountermodality.model.aggregate.EncounterModality;
import com.mindconnect.domain.encountermodality.model.valueobject.EncounterModalityId;
import com.mindconnect.domain.encountermodality.port.repository.EncounterModalityRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DeleteEncounterModalityUseCaseTest {

    @Test
    void eliminaUnRegistroExistente() {
        EncounterModality aggregate = EncounterModality.register("valor-a", "valor-a");
        FakeEncounterModalityRepository repository = new FakeEncounterModalityRepository();
        repository.save(aggregate);
        DeleteEncounterModalityUseCase useCase = new DeleteEncounterModalityUseCase(repository);

        EncounterModalityDeletedEvent event = useCase.execute(aggregate.id());

        assertSame(aggregate, repository.deleted);
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void rechazaEliminarUnRegistroQueNoExiste() {
        FakeEncounterModalityRepository repository = new FakeEncounterModalityRepository();
        DeleteEncounterModalityUseCase useCase = new DeleteEncounterModalityUseCase(repository);

        assertThrows(EncounterModalityNotFoundApplicationException.class, () -> useCase.execute(EncounterModalityId.generate()));
        assertNull(repository.deleted);
    }

    /** Repositorio falso en memoria: así se prueba el caso de uso sin base de datos. */
    private static final class FakeEncounterModalityRepository implements EncounterModalityRepository {
        private final Map<EncounterModalityId, EncounterModality> store = new LinkedHashMap<>();
        private EncounterModality deleted;

        @Override
        public EncounterModality save(EncounterModality aggregate) {
            store.put(aggregate.id(), aggregate);
            return aggregate;
        }

        @Override
        public Optional<EncounterModality> findById(EncounterModalityId id) {
            return Optional.ofNullable(store.get(id));
        }

        @Override
        public List<EncounterModality> findAll() {
            return new ArrayList<>(store.values());
        }

        @Override
        public void delete(EncounterModality aggregate) {
            deleted = aggregate;
            store.remove(aggregate.id());
        }
    }
}
