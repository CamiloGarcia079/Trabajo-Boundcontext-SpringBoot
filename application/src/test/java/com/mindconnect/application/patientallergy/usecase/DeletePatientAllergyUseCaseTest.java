package com.mindconnect.application.patientallergy.usecase;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import com.mindconnect.application.patientallergy.exception.PatientAllergyNotFoundApplicationException;
import com.mindconnect.domain.patientallergy.event.PatientAllergyDeletedEvent;
import com.mindconnect.domain.patientallergy.model.aggregate.PatientAllergy;
import com.mindconnect.domain.patientallergy.model.valueobject.PatientAllergyId;
import com.mindconnect.domain.patientallergy.port.repository.PatientAllergyRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DeletePatientAllergyUseCaseTest {

    @Test
    void eliminaUnRegistroExistente() {
        PatientAllergy aggregate = PatientAllergy.register(UUID.randomUUID(), "valor-a", "valor-a", "valor-a", LocalDateTime.now(), UUID.randomUUID());
        FakePatientAllergyRepository repository = new FakePatientAllergyRepository();
        repository.save(aggregate);
        DeletePatientAllergyUseCase useCase = new DeletePatientAllergyUseCase(repository);

        PatientAllergyDeletedEvent event = useCase.execute(aggregate.id());

        assertSame(aggregate, repository.deleted);
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void rechazaEliminarUnRegistroQueNoExiste() {
        FakePatientAllergyRepository repository = new FakePatientAllergyRepository();
        DeletePatientAllergyUseCase useCase = new DeletePatientAllergyUseCase(repository);

        assertThrows(PatientAllergyNotFoundApplicationException.class, () -> useCase.execute(PatientAllergyId.generate()));
        assertNull(repository.deleted);
    }

    /** Repositorio falso en memoria: así se prueba el caso de uso sin base de datos. */
    private static final class FakePatientAllergyRepository implements PatientAllergyRepository {
        private final Map<PatientAllergyId, PatientAllergy> store = new LinkedHashMap<>();
        private PatientAllergy deleted;

        @Override
        public PatientAllergy save(PatientAllergy aggregate) {
            store.put(aggregate.id(), aggregate);
            return aggregate;
        }

        @Override
        public Optional<PatientAllergy> findById(PatientAllergyId id) {
            return Optional.ofNullable(store.get(id));
        }

        @Override
        public List<PatientAllergy> findAll() {
            return new ArrayList<>(store.values());
        }

        @Override
        public void delete(PatientAllergy aggregate) {
            deleted = aggregate;
            store.remove(aggregate.id());
        }
    }
}
