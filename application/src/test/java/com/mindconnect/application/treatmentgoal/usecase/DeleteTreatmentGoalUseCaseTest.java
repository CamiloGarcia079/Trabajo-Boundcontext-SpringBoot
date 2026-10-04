package com.mindconnect.application.treatmentgoal.usecase;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import com.mindconnect.application.treatmentgoal.exception.TreatmentGoalNotFoundApplicationException;
import com.mindconnect.domain.treatmentgoal.event.TreatmentGoalDeletedEvent;
import com.mindconnect.domain.treatmentgoal.model.aggregate.TreatmentGoal;
import com.mindconnect.domain.treatmentgoal.model.valueobject.TreatmentGoalId;
import com.mindconnect.domain.treatmentgoal.port.repository.TreatmentGoalRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DeleteTreatmentGoalUseCaseTest {

    @Test
    void eliminaUnRegistroExistente() {
        TreatmentGoal aggregate = TreatmentGoal.register(UUID.randomUUID(), "valor-a", LocalDate.now(), LocalDateTime.now(), "valor-a", UUID.randomUUID());
        FakeTreatmentGoalRepository repository = new FakeTreatmentGoalRepository();
        repository.save(aggregate);
        DeleteTreatmentGoalUseCase useCase = new DeleteTreatmentGoalUseCase(repository);

        TreatmentGoalDeletedEvent event = useCase.execute(aggregate.id());

        assertSame(aggregate, repository.deleted);
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void rechazaEliminarUnRegistroQueNoExiste() {
        FakeTreatmentGoalRepository repository = new FakeTreatmentGoalRepository();
        DeleteTreatmentGoalUseCase useCase = new DeleteTreatmentGoalUseCase(repository);

        assertThrows(TreatmentGoalNotFoundApplicationException.class, () -> useCase.execute(TreatmentGoalId.generate()));
        assertNull(repository.deleted);
    }

    /** Repositorio falso en memoria: así se prueba el caso de uso sin base de datos. */
    private static final class FakeTreatmentGoalRepository implements TreatmentGoalRepository {
        private final Map<TreatmentGoalId, TreatmentGoal> store = new LinkedHashMap<>();
        private TreatmentGoal deleted;

        @Override
        public TreatmentGoal save(TreatmentGoal aggregate) {
            store.put(aggregate.id(), aggregate);
            return aggregate;
        }

        @Override
        public Optional<TreatmentGoal> findById(TreatmentGoalId id) {
            return Optional.ofNullable(store.get(id));
        }

        @Override
        public List<TreatmentGoal> findAll() {
            return new ArrayList<>(store.values());
        }

        @Override
        public void delete(TreatmentGoal aggregate) {
            deleted = aggregate;
            store.remove(aggregate.id());
        }
    }
}
