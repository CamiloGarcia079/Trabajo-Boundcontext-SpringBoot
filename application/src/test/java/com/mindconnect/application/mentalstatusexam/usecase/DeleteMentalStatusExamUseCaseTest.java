package com.mindconnect.application.mentalstatusexam.usecase;

import java.util.UUID;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import com.mindconnect.application.mentalstatusexam.exception.MentalStatusExamNotFoundApplicationException;
import com.mindconnect.domain.mentalstatusexam.event.MentalStatusExamDeletedEvent;
import com.mindconnect.domain.mentalstatusexam.model.aggregate.MentalStatusExam;
import com.mindconnect.domain.mentalstatusexam.model.valueobject.MentalStatusExamId;
import com.mindconnect.domain.mentalstatusexam.port.repository.MentalStatusExamRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DeleteMentalStatusExamUseCaseTest {

    @Test
    void eliminaUnRegistroExistente() {
        MentalStatusExam aggregate = MentalStatusExam.register(UUID.randomUUID(), "valor-a", "valor-a", "valor-a", "valor-a", "valor-a", "valor-a", "valor-a", "valor-a", "valor-a", "valor-a", "valor-a", "valor-a", "valor-a", "valor-a", "valor-a", "valor-a", "valor-a", UUID.randomUUID());
        FakeMentalStatusExamRepository repository = new FakeMentalStatusExamRepository();
        repository.save(aggregate);
        DeleteMentalStatusExamUseCase useCase = new DeleteMentalStatusExamUseCase(repository);

        MentalStatusExamDeletedEvent event = useCase.execute(aggregate.id());

        assertSame(aggregate, repository.deleted);
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void rechazaEliminarUnRegistroQueNoExiste() {
        FakeMentalStatusExamRepository repository = new FakeMentalStatusExamRepository();
        DeleteMentalStatusExamUseCase useCase = new DeleteMentalStatusExamUseCase(repository);

        assertThrows(MentalStatusExamNotFoundApplicationException.class, () -> useCase.execute(MentalStatusExamId.generate()));
        assertNull(repository.deleted);
    }

    /** Repositorio falso en memoria: así se prueba el caso de uso sin base de datos. */
    private static final class FakeMentalStatusExamRepository implements MentalStatusExamRepository {
        private final Map<MentalStatusExamId, MentalStatusExam> store = new LinkedHashMap<>();
        private MentalStatusExam deleted;

        @Override
        public MentalStatusExam save(MentalStatusExam aggregate) {
            store.put(aggregate.id(), aggregate);
            return aggregate;
        }

        @Override
        public Optional<MentalStatusExam> findById(MentalStatusExamId id) {
            return Optional.ofNullable(store.get(id));
        }

        @Override
        public List<MentalStatusExam> findAll() {
            return new ArrayList<>(store.values());
        }

        @Override
        public void delete(MentalStatusExam aggregate) {
            deleted = aggregate;
            store.remove(aggregate.id());
        }
    }
}
