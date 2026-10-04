package com.mindconnect.application.clinicalrecord.usecase;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import com.mindconnect.application.clinicalrecord.exception.ClinicalRecordNotFoundApplicationException;
import com.mindconnect.domain.clinicalrecord.event.ClinicalRecordDeletedEvent;
import com.mindconnect.domain.clinicalrecord.model.aggregate.ClinicalRecord;
import com.mindconnect.domain.clinicalrecord.model.valueobject.ClinicalRecordId;
import com.mindconnect.domain.clinicalrecord.port.repository.ClinicalRecordRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DeleteClinicalRecordUseCaseTest {

    @Test
    void eliminaUnRegistroExistente() {
        ClinicalRecord aggregate = ClinicalRecord.register(UUID.randomUUID(), LocalDateTime.now(), "valor-a", LocalDateTime.now(), LocalDateTime.now(), UUID.randomUUID(), UUID.randomUUID());
        FakeClinicalRecordRepository repository = new FakeClinicalRecordRepository();
        repository.save(aggregate);
        DeleteClinicalRecordUseCase useCase = new DeleteClinicalRecordUseCase(repository);

        ClinicalRecordDeletedEvent event = useCase.execute(aggregate.id());

        assertSame(aggregate, repository.deleted);
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void rechazaEliminarUnRegistroQueNoExiste() {
        FakeClinicalRecordRepository repository = new FakeClinicalRecordRepository();
        DeleteClinicalRecordUseCase useCase = new DeleteClinicalRecordUseCase(repository);

        assertThrows(ClinicalRecordNotFoundApplicationException.class, () -> useCase.execute(ClinicalRecordId.generate()));
        assertNull(repository.deleted);
    }

    /** Repositorio falso en memoria: así se prueba el caso de uso sin base de datos. */
    private static final class FakeClinicalRecordRepository implements ClinicalRecordRepository {
        private final Map<ClinicalRecordId, ClinicalRecord> store = new LinkedHashMap<>();
        private ClinicalRecord deleted;

        @Override
        public ClinicalRecord save(ClinicalRecord aggregate) {
            store.put(aggregate.id(), aggregate);
            return aggregate;
        }

        @Override
        public Optional<ClinicalRecord> findById(ClinicalRecordId id) {
            return Optional.ofNullable(store.get(id));
        }

        @Override
        public List<ClinicalRecord> findAll() {
            return new ArrayList<>(store.values());
        }

        @Override
        public void delete(ClinicalRecord aggregate) {
            deleted = aggregate;
            store.remove(aggregate.id());
        }
    }
}
