package com.mindconnect.application.patient.usecase;

import java.time.LocalDate;
import java.util.UUID;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import com.mindconnect.application.patient.exception.PatientNotFoundApplicationException;
import com.mindconnect.domain.patient.event.PatientDeletedEvent;
import com.mindconnect.domain.patient.model.aggregate.Patient;
import com.mindconnect.domain.patient.model.valueobject.PatientId;
import com.mindconnect.domain.patient.port.repository.PatientRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DeletePatientUseCaseTest {

    @Test
    void eliminaUnRegistroExistente() {
        Patient aggregate = Patient.register(UUID.randomUUID(), "valor-a", "valor-a", "valor-a", "valor-a", "valor-a", LocalDate.now(), UUID.randomUUID(), UUID.randomUUID(), "valor-a", "valor-a", "valor-a", UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID());
        FakePatientRepository repository = new FakePatientRepository();
        repository.save(aggregate);
        DeletePatientUseCase useCase = new DeletePatientUseCase(repository);

        PatientDeletedEvent event = useCase.execute(aggregate.id());

        assertSame(aggregate, repository.deleted);
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void rechazaEliminarUnRegistroQueNoExiste() {
        FakePatientRepository repository = new FakePatientRepository();
        DeletePatientUseCase useCase = new DeletePatientUseCase(repository);

        assertThrows(PatientNotFoundApplicationException.class, () -> useCase.execute(PatientId.generate()));
        assertNull(repository.deleted);
    }

    /** Repositorio falso en memoria: así se prueba el caso de uso sin base de datos. */
    private static final class FakePatientRepository implements PatientRepository {
        private final Map<PatientId, Patient> store = new LinkedHashMap<>();
        private Patient deleted;

        @Override
        public Patient save(Patient aggregate) {
            store.put(aggregate.id(), aggregate);
            return aggregate;
        }

        @Override
        public Optional<Patient> findById(PatientId id) {
            return Optional.ofNullable(store.get(id));
        }

        @Override
        public List<Patient> findAll() {
            return new ArrayList<>(store.values());
        }

        @Override
        public void delete(Patient aggregate) {
            deleted = aggregate;
            store.remove(aggregate.id());
        }
    }
}
