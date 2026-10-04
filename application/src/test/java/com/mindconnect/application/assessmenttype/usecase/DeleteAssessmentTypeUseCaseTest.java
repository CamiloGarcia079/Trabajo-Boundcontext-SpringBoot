package com.mindconnect.application.assessmenttype.usecase;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import com.mindconnect.application.assessmenttype.exception.AssessmentTypeNotFoundApplicationException;
import com.mindconnect.domain.assessmenttype.event.AssessmentTypeDeletedEvent;
import com.mindconnect.domain.assessmenttype.model.aggregate.AssessmentType;
import com.mindconnect.domain.assessmenttype.model.valueobject.AssessmentTypeId;
import com.mindconnect.domain.assessmenttype.port.repository.AssessmentTypeRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DeleteAssessmentTypeUseCaseTest {

    @Test
    void eliminaUnRegistroExistente() {
        AssessmentType aggregate = AssessmentType.register("valor-a", "valor-a", "valor-a");
        FakeAssessmentTypeRepository repository = new FakeAssessmentTypeRepository();
        repository.save(aggregate);
        DeleteAssessmentTypeUseCase useCase = new DeleteAssessmentTypeUseCase(repository);

        AssessmentTypeDeletedEvent event = useCase.execute(aggregate.id());

        assertSame(aggregate, repository.deleted);
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void rechazaEliminarUnRegistroQueNoExiste() {
        FakeAssessmentTypeRepository repository = new FakeAssessmentTypeRepository();
        DeleteAssessmentTypeUseCase useCase = new DeleteAssessmentTypeUseCase(repository);

        assertThrows(AssessmentTypeNotFoundApplicationException.class, () -> useCase.execute(AssessmentTypeId.generate()));
        assertNull(repository.deleted);
    }

    /** Repositorio falso en memoria: así se prueba el caso de uso sin base de datos. */
    private static final class FakeAssessmentTypeRepository implements AssessmentTypeRepository {
        private final Map<AssessmentTypeId, AssessmentType> store = new LinkedHashMap<>();
        private AssessmentType deleted;

        @Override
        public AssessmentType save(AssessmentType aggregate) {
            store.put(aggregate.id(), aggregate);
            return aggregate;
        }

        @Override
        public Optional<AssessmentType> findById(AssessmentTypeId id) {
            return Optional.ofNullable(store.get(id));
        }

        @Override
        public List<AssessmentType> findAll() {
            return new ArrayList<>(store.values());
        }

        @Override
        public void delete(AssessmentType aggregate) {
            deleted = aggregate;
            store.remove(aggregate.id());
        }
    }
}
