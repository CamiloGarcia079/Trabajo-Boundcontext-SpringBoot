package com.mindconnect.application.clinicalrecordstatus.usecase;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import com.mindconnect.application.clinicalrecordstatus.exception.ClinicalRecordStatusNotFoundApplicationException;
import com.mindconnect.domain.clinicalrecordstatus.event.ClinicalRecordStatusDeletedEvent;
import com.mindconnect.domain.clinicalrecordstatus.model.aggregate.ClinicalRecordStatus;
import com.mindconnect.domain.clinicalrecordstatus.model.valueobject.ClinicalRecordStatusId;
import com.mindconnect.domain.clinicalrecordstatus.port.repository.ClinicalRecordStatusRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DeleteClinicalRecordStatusUseCaseTest {

    @Test
    void eliminaUnRegistroExistente() {
        ClinicalRecordStatus aggregate = ClinicalRecordStatus.register("valor-a", "valor-a");
        FakeClinicalRecordStatusRepository repository = new FakeClinicalRecordStatusRepository();
        repository.save(aggregate);
        DeleteClinicalRecordStatusUseCase useCase = new DeleteClinicalRecordStatusUseCase(repository);

        ClinicalRecordStatusDeletedEvent event = useCase.execute(aggregate.id());

        assertSame(aggregate, repository.deleted);
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void rechazaEliminarUnRegistroQueNoExiste() {
        FakeClinicalRecordStatusRepository repository = new FakeClinicalRecordStatusRepository();
        DeleteClinicalRecordStatusUseCase useCase = new DeleteClinicalRecordStatusUseCase(repository);

        assertThrows(ClinicalRecordStatusNotFoundApplicationException.class, () -> useCase.execute(ClinicalRecordStatusId.generate()));
        assertNull(repository.deleted);
    }

    /** Repositorio falso en memoria: así se prueba el caso de uso sin base de datos. */
    private static final class FakeClinicalRecordStatusRepository implements ClinicalRecordStatusRepository {
        private final Map<ClinicalRecordStatusId, ClinicalRecordStatus> store = new LinkedHashMap<>();
        private ClinicalRecordStatus deleted;

        @Override
        public ClinicalRecordStatus save(ClinicalRecordStatus aggregate) {
            store.put(aggregate.id(), aggregate);
            return aggregate;
        }

        @Override
        public Optional<ClinicalRecordStatus> findById(ClinicalRecordStatusId id) {
            return Optional.ofNullable(store.get(id));
        }

        @Override
        public List<ClinicalRecordStatus> findAll() {
            return new ArrayList<>(store.values());
        }

        @Override
        public void delete(ClinicalRecordStatus aggregate) {
            deleted = aggregate;
            store.remove(aggregate.id());
        }
    }
}
