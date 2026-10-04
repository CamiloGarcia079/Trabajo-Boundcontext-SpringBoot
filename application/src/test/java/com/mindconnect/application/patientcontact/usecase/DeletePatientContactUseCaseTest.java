package com.mindconnect.application.patientcontact.usecase;

import java.util.UUID;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import com.mindconnect.application.patientcontact.exception.PatientContactNotFoundApplicationException;
import com.mindconnect.domain.patientcontact.event.PatientContactDeletedEvent;
import com.mindconnect.domain.patientcontact.model.aggregate.PatientContact;
import com.mindconnect.domain.patientcontact.model.valueobject.PatientContactId;
import com.mindconnect.domain.patientcontact.port.repository.PatientContactRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DeletePatientContactUseCaseTest {

    @Test
    void eliminaUnRegistroExistente() {
        PatientContact aggregate = PatientContact.register(UUID.randomUUID(), UUID.randomUUID(), true, true, UUID.randomUUID());
        FakePatientContactRepository repository = new FakePatientContactRepository();
        repository.save(aggregate);
        DeletePatientContactUseCase useCase = new DeletePatientContactUseCase(repository);

        PatientContactDeletedEvent event = useCase.execute(aggregate.id());

        assertSame(aggregate, repository.deleted);
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void rechazaEliminarUnRegistroQueNoExiste() {
        FakePatientContactRepository repository = new FakePatientContactRepository();
        DeletePatientContactUseCase useCase = new DeletePatientContactUseCase(repository);

        assertThrows(PatientContactNotFoundApplicationException.class, () -> useCase.execute(PatientContactId.generate()));
        assertNull(repository.deleted);
    }

    /** Repositorio falso en memoria: así se prueba el caso de uso sin base de datos. */
    private static final class FakePatientContactRepository implements PatientContactRepository {
        private final Map<PatientContactId, PatientContact> store = new LinkedHashMap<>();
        private PatientContact deleted;

        @Override
        public PatientContact save(PatientContact aggregate) {
            store.put(aggregate.id(), aggregate);
            return aggregate;
        }

        @Override
        public Optional<PatientContact> findById(PatientContactId id) {
            return Optional.ofNullable(store.get(id));
        }

        @Override
        public List<PatientContact> findAll() {
            return new ArrayList<>(store.values());
        }

        @Override
        public void delete(PatientContact aggregate) {
            deleted = aggregate;
            store.remove(aggregate.id());
        }
    }
}
