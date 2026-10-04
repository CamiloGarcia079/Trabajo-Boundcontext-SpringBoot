package com.mindconnect.application.risklevel.usecase;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import com.mindconnect.application.risklevel.exception.RiskLevelNotFoundApplicationException;
import com.mindconnect.domain.risklevel.event.RiskLevelDeletedEvent;
import com.mindconnect.domain.risklevel.model.aggregate.RiskLevel;
import com.mindconnect.domain.risklevel.model.valueobject.RiskLevelId;
import com.mindconnect.domain.risklevel.port.repository.RiskLevelRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DeleteRiskLevelUseCaseTest {

    @Test
    void eliminaUnRegistroExistente() {
        RiskLevel aggregate = RiskLevel.register("valor-a", "valor-a", 1);
        FakeRiskLevelRepository repository = new FakeRiskLevelRepository();
        repository.save(aggregate);
        DeleteRiskLevelUseCase useCase = new DeleteRiskLevelUseCase(repository);

        RiskLevelDeletedEvent event = useCase.execute(aggregate.id());

        assertSame(aggregate, repository.deleted);
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void rechazaEliminarUnRegistroQueNoExiste() {
        FakeRiskLevelRepository repository = new FakeRiskLevelRepository();
        DeleteRiskLevelUseCase useCase = new DeleteRiskLevelUseCase(repository);

        assertThrows(RiskLevelNotFoundApplicationException.class, () -> useCase.execute(RiskLevelId.generate()));
        assertNull(repository.deleted);
    }

    /** Repositorio falso en memoria: así se prueba el caso de uso sin base de datos. */
    private static final class FakeRiskLevelRepository implements RiskLevelRepository {
        private final Map<RiskLevelId, RiskLevel> store = new LinkedHashMap<>();
        private RiskLevel deleted;

        @Override
        public RiskLevel save(RiskLevel aggregate) {
            store.put(aggregate.id(), aggregate);
            return aggregate;
        }

        @Override
        public Optional<RiskLevel> findById(RiskLevelId id) {
            return Optional.ofNullable(store.get(id));
        }

        @Override
        public List<RiskLevel> findAll() {
            return new ArrayList<>(store.values());
        }

        @Override
        public void delete(RiskLevel aggregate) {
            deleted = aggregate;
            store.remove(aggregate.id());
        }
    }
}
