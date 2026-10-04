package com.mindconnect.application.professionaltype.usecase;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import com.mindconnect.application.professionaltype.exception.ProfessionalTypeNotFoundApplicationException;
import com.mindconnect.domain.professionaltype.event.ProfessionalTypeDeletedEvent;
import com.mindconnect.domain.professionaltype.model.aggregate.ProfessionalType;
import com.mindconnect.domain.professionaltype.model.valueobject.ProfessionalTypeId;
import com.mindconnect.domain.professionaltype.port.repository.ProfessionalTypeRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DeleteProfessionalTypeUseCaseTest {

    @Test
    void eliminaUnRegistroExistente() {
        ProfessionalType aggregate = ProfessionalType.register("valor-a");
        FakeProfessionalTypeRepository repository = new FakeProfessionalTypeRepository();
        repository.save(aggregate);
        DeleteProfessionalTypeUseCase useCase = new DeleteProfessionalTypeUseCase(repository);

        ProfessionalTypeDeletedEvent event = useCase.execute(aggregate.id());

        assertSame(aggregate, repository.deleted);
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void rechazaEliminarUnRegistroQueNoExiste() {
        FakeProfessionalTypeRepository repository = new FakeProfessionalTypeRepository();
        DeleteProfessionalTypeUseCase useCase = new DeleteProfessionalTypeUseCase(repository);

        assertThrows(ProfessionalTypeNotFoundApplicationException.class, () -> useCase.execute(ProfessionalTypeId.generate()));
        assertNull(repository.deleted);
    }

    /** Repositorio falso en memoria: así se prueba el caso de uso sin base de datos. */
    private static final class FakeProfessionalTypeRepository implements ProfessionalTypeRepository {
        private final Map<ProfessionalTypeId, ProfessionalType> store = new LinkedHashMap<>();
        private ProfessionalType deleted;

        @Override
        public ProfessionalType save(ProfessionalType aggregate) {
            store.put(aggregate.id(), aggregate);
            return aggregate;
        }

        @Override
        public Optional<ProfessionalType> findById(ProfessionalTypeId id) {
            return Optional.ofNullable(store.get(id));
        }

        @Override
        public List<ProfessionalType> findAll() {
            return new ArrayList<>(store.values());
        }

        @Override
        public void delete(ProfessionalType aggregate) {
            deleted = aggregate;
            store.remove(aggregate.id());
        }
    }
}
