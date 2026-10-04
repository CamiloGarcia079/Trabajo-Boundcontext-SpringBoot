package com.mindconnect.application.gender.usecase;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import com.mindconnect.application.gender.exception.GenderNotFoundApplicationException;
import com.mindconnect.domain.gender.event.GenderDeletedEvent;
import com.mindconnect.domain.gender.model.aggregate.Gender;
import com.mindconnect.domain.gender.model.valueobject.GenderId;
import com.mindconnect.domain.gender.port.repository.GenderRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DeleteGenderUseCaseTest {

    @Test
    void eliminaUnRegistroExistente() {
        Gender aggregate = Gender.register("valor-a");
        FakeGenderRepository repository = new FakeGenderRepository();
        repository.save(aggregate);
        DeleteGenderUseCase useCase = new DeleteGenderUseCase(repository);

        GenderDeletedEvent event = useCase.execute(aggregate.id());

        assertSame(aggregate, repository.deleted);
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void rechazaEliminarUnRegistroQueNoExiste() {
        FakeGenderRepository repository = new FakeGenderRepository();
        DeleteGenderUseCase useCase = new DeleteGenderUseCase(repository);

        assertThrows(GenderNotFoundApplicationException.class, () -> useCase.execute(GenderId.generate()));
        assertNull(repository.deleted);
    }

    /** Repositorio falso en memoria: así se prueba el caso de uso sin base de datos. */
    private static final class FakeGenderRepository implements GenderRepository {
        private final Map<GenderId, Gender> store = new LinkedHashMap<>();
        private Gender deleted;

        @Override
        public Gender save(Gender aggregate) {
            store.put(aggregate.id(), aggregate);
            return aggregate;
        }

        @Override
        public Optional<Gender> findById(GenderId id) {
            return Optional.ofNullable(store.get(id));
        }

        @Override
        public List<Gender> findAll() {
            return new ArrayList<>(store.values());
        }

        @Override
        public void delete(Gender aggregate) {
            deleted = aggregate;
            store.remove(aggregate.id());
        }
    }
}
