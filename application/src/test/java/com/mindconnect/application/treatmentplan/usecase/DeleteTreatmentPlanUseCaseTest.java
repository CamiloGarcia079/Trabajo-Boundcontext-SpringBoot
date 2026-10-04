package com.mindconnect.application.treatmentplan.usecase;

import java.time.LocalDate;
import java.util.UUID;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import com.mindconnect.application.treatmentplan.exception.TreatmentPlanNotFoundApplicationException;
import com.mindconnect.domain.treatmentplan.event.TreatmentPlanDeletedEvent;
import com.mindconnect.domain.treatmentplan.model.aggregate.TreatmentPlan;
import com.mindconnect.domain.treatmentplan.model.valueobject.TreatmentPlanId;
import com.mindconnect.domain.treatmentplan.port.repository.TreatmentPlanRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DeleteTreatmentPlanUseCaseTest {

    @Test
    void eliminaUnRegistroExistente() {
        TreatmentPlan aggregate = TreatmentPlan.register(UUID.randomUUID(), UUID.randomUUID(), "valor-a", "valor-a", LocalDate.now(), LocalDate.now(), UUID.randomUUID());
        FakeTreatmentPlanRepository repository = new FakeTreatmentPlanRepository();
        repository.save(aggregate);
        DeleteTreatmentPlanUseCase useCase = new DeleteTreatmentPlanUseCase(repository);

        TreatmentPlanDeletedEvent event = useCase.execute(aggregate.id());

        assertSame(aggregate, repository.deleted);
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void rechazaEliminarUnRegistroQueNoExiste() {
        FakeTreatmentPlanRepository repository = new FakeTreatmentPlanRepository();
        DeleteTreatmentPlanUseCase useCase = new DeleteTreatmentPlanUseCase(repository);

        assertThrows(TreatmentPlanNotFoundApplicationException.class, () -> useCase.execute(TreatmentPlanId.generate()));
        assertNull(repository.deleted);
    }

    /** Repositorio falso en memoria: así se prueba el caso de uso sin base de datos. */
    private static final class FakeTreatmentPlanRepository implements TreatmentPlanRepository {
        private final Map<TreatmentPlanId, TreatmentPlan> store = new LinkedHashMap<>();
        private TreatmentPlan deleted;

        @Override
        public TreatmentPlan save(TreatmentPlan aggregate) {
            store.put(aggregate.id(), aggregate);
            return aggregate;
        }

        @Override
        public Optional<TreatmentPlan> findById(TreatmentPlanId id) {
            return Optional.ofNullable(store.get(id));
        }

        @Override
        public List<TreatmentPlan> findAll() {
            return new ArrayList<>(store.values());
        }

        @Override
        public void delete(TreatmentPlan aggregate) {
            deleted = aggregate;
            store.remove(aggregate.id());
        }
    }
}
