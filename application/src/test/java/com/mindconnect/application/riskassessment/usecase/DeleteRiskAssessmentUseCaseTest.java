package com.mindconnect.application.riskassessment.usecase;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import com.mindconnect.application.riskassessment.exception.RiskAssessmentNotFoundApplicationException;
import com.mindconnect.domain.riskassessment.event.RiskAssessmentDeletedEvent;
import com.mindconnect.domain.riskassessment.model.aggregate.RiskAssessment;
import com.mindconnect.domain.riskassessment.model.valueobject.RiskAssessmentId;
import com.mindconnect.domain.riskassessment.port.repository.RiskAssessmentRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DeleteRiskAssessmentUseCaseTest {

    @Test
    void eliminaUnRegistroExistente() {
        RiskAssessment aggregate = RiskAssessment.register(UUID.randomUUID(), UUID.randomUUID(), true, true, true, true, true, "valor-a", "valor-a", "valor-a", "valor-a", LocalDateTime.now(), UUID.randomUUID());
        FakeRiskAssessmentRepository repository = new FakeRiskAssessmentRepository();
        repository.save(aggregate);
        DeleteRiskAssessmentUseCase useCase = new DeleteRiskAssessmentUseCase(repository);

        RiskAssessmentDeletedEvent event = useCase.execute(aggregate.id());

        assertSame(aggregate, repository.deleted);
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void rechazaEliminarUnRegistroQueNoExiste() {
        FakeRiskAssessmentRepository repository = new FakeRiskAssessmentRepository();
        DeleteRiskAssessmentUseCase useCase = new DeleteRiskAssessmentUseCase(repository);

        assertThrows(RiskAssessmentNotFoundApplicationException.class, () -> useCase.execute(RiskAssessmentId.generate()));
        assertNull(repository.deleted);
    }

    /** Repositorio falso en memoria: así se prueba el caso de uso sin base de datos. */
    private static final class FakeRiskAssessmentRepository implements RiskAssessmentRepository {
        private final Map<RiskAssessmentId, RiskAssessment> store = new LinkedHashMap<>();
        private RiskAssessment deleted;

        @Override
        public RiskAssessment save(RiskAssessment aggregate) {
            store.put(aggregate.id(), aggregate);
            return aggregate;
        }

        @Override
        public Optional<RiskAssessment> findById(RiskAssessmentId id) {
            return Optional.ofNullable(store.get(id));
        }

        @Override
        public List<RiskAssessment> findAll() {
            return new ArrayList<>(store.values());
        }

        @Override
        public void delete(RiskAssessment aggregate) {
            deleted = aggregate;
            store.remove(aggregate.id());
        }
    }
}
