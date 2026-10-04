package com.mindconnect.application.clinicalnote.usecase;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import com.mindconnect.application.clinicalnote.exception.ClinicalNoteNotFoundApplicationException;
import com.mindconnect.domain.clinicalnote.event.ClinicalNoteDeletedEvent;
import com.mindconnect.domain.clinicalnote.model.aggregate.ClinicalNote;
import com.mindconnect.domain.clinicalnote.model.valueobject.ClinicalNoteId;
import com.mindconnect.domain.clinicalnote.port.repository.ClinicalNoteRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DeleteClinicalNoteUseCaseTest {

    @Test
    void eliminaUnRegistroExistente() {
        ClinicalNote aggregate = ClinicalNote.register(UUID.randomUUID(), UUID.randomUUID(), "valor-a", "valor-a", "valor-a", "valor-a", "valor-a", LocalDateTime.now());
        FakeClinicalNoteRepository repository = new FakeClinicalNoteRepository();
        repository.save(aggregate);
        DeleteClinicalNoteUseCase useCase = new DeleteClinicalNoteUseCase(repository);

        ClinicalNoteDeletedEvent event = useCase.execute(aggregate.id());

        assertSame(aggregate, repository.deleted);
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void rechazaEliminarUnRegistroQueNoExiste() {
        FakeClinicalNoteRepository repository = new FakeClinicalNoteRepository();
        DeleteClinicalNoteUseCase useCase = new DeleteClinicalNoteUseCase(repository);

        assertThrows(ClinicalNoteNotFoundApplicationException.class, () -> useCase.execute(ClinicalNoteId.generate()));
        assertNull(repository.deleted);
    }

    /** Repositorio falso en memoria: así se prueba el caso de uso sin base de datos. */
    private static final class FakeClinicalNoteRepository implements ClinicalNoteRepository {
        private final Map<ClinicalNoteId, ClinicalNote> store = new LinkedHashMap<>();
        private ClinicalNote deleted;

        @Override
        public ClinicalNote save(ClinicalNote aggregate) {
            store.put(aggregate.id(), aggregate);
            return aggregate;
        }

        @Override
        public Optional<ClinicalNote> findById(ClinicalNoteId id) {
            return Optional.ofNullable(store.get(id));
        }

        @Override
        public List<ClinicalNote> findAll() {
            return new ArrayList<>(store.values());
        }

        @Override
        public void delete(ClinicalNote aggregate) {
            deleted = aggregate;
            store.remove(aggregate.id());
        }
    }
}
