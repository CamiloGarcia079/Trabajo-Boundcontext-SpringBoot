package com.mindconnect.application.professionalstudy.usecase;

import java.util.UUID;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import com.mindconnect.application.professionalstudy.exception.ProfessionalStudyNotFoundApplicationException;
import com.mindconnect.domain.professionalstudy.event.ProfessionalStudyDeletedEvent;
import com.mindconnect.domain.professionalstudy.model.aggregate.ProfessionalStudy;
import com.mindconnect.domain.professionalstudy.model.valueobject.ProfessionalStudyId;
import com.mindconnect.domain.professionalstudy.port.repository.ProfessionalStudyRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DeleteProfessionalStudyUseCaseTest {

    @Test
    void eliminaUnRegistroExistente() {
        ProfessionalStudy aggregate = ProfessionalStudy.register(UUID.randomUUID(), UUID.randomUUID(), "valor-a", "valor-a", true, "valor-a", UUID.randomUUID());
        FakeProfessionalStudyRepository repository = new FakeProfessionalStudyRepository();
        repository.save(aggregate);
        DeleteProfessionalStudyUseCase useCase = new DeleteProfessionalStudyUseCase(repository);

        ProfessionalStudyDeletedEvent event = useCase.execute(aggregate.id());

        assertSame(aggregate, repository.deleted);
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void rechazaEliminarUnRegistroQueNoExiste() {
        FakeProfessionalStudyRepository repository = new FakeProfessionalStudyRepository();
        DeleteProfessionalStudyUseCase useCase = new DeleteProfessionalStudyUseCase(repository);

        assertThrows(ProfessionalStudyNotFoundApplicationException.class, () -> useCase.execute(ProfessionalStudyId.generate()));
        assertNull(repository.deleted);
    }

    /** Repositorio falso en memoria: así se prueba el caso de uso sin base de datos. */
    private static final class FakeProfessionalStudyRepository implements ProfessionalStudyRepository {
        private final Map<ProfessionalStudyId, ProfessionalStudy> store = new LinkedHashMap<>();
        private ProfessionalStudy deleted;

        @Override
        public ProfessionalStudy save(ProfessionalStudy aggregate) {
            store.put(aggregate.id(), aggregate);
            return aggregate;
        }

        @Override
        public Optional<ProfessionalStudy> findById(ProfessionalStudyId id) {
            return Optional.ofNullable(store.get(id));
        }

        @Override
        public List<ProfessionalStudy> findAll() {
            return new ArrayList<>(store.values());
        }

        @Override
        public void delete(ProfessionalStudy aggregate) {
            deleted = aggregate;
            store.remove(aggregate.id());
        }
    }
}
