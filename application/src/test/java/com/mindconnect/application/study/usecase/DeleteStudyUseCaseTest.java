package com.mindconnect.application.study.usecase;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import com.mindconnect.application.study.exception.StudyNotFoundApplicationException;
import com.mindconnect.domain.study.event.StudyDeletedEvent;
import com.mindconnect.domain.study.model.aggregate.Study;
import com.mindconnect.domain.study.model.valueobject.StudyId;
import com.mindconnect.domain.study.port.repository.StudyRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DeleteStudyUseCaseTest {

    @Test
    void eliminaUnRegistroExistente() {
        Study aggregate = Study.register("valor-a");
        FakeStudyRepository repository = new FakeStudyRepository();
        repository.save(aggregate);
        DeleteStudyUseCase useCase = new DeleteStudyUseCase(repository);

        StudyDeletedEvent event = useCase.execute(aggregate.id());

        assertSame(aggregate, repository.deleted);
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void rechazaEliminarUnRegistroQueNoExiste() {
        FakeStudyRepository repository = new FakeStudyRepository();
        DeleteStudyUseCase useCase = new DeleteStudyUseCase(repository);

        assertThrows(StudyNotFoundApplicationException.class, () -> useCase.execute(StudyId.generate()));
        assertNull(repository.deleted);
    }

    /** Repositorio falso en memoria: así se prueba el caso de uso sin base de datos. */
    private static final class FakeStudyRepository implements StudyRepository {
        private final Map<StudyId, Study> store = new LinkedHashMap<>();
        private Study deleted;

        @Override
        public Study save(Study aggregate) {
            store.put(aggregate.id(), aggregate);
            return aggregate;
        }

        @Override
        public Optional<Study> findById(StudyId id) {
            return Optional.ofNullable(store.get(id));
        }

        @Override
        public List<Study> findAll() {
            return new ArrayList<>(store.values());
        }

        @Override
        public void delete(Study aggregate) {
            deleted = aggregate;
            store.remove(aggregate.id());
        }
    }
}
