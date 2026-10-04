package com.mindconnect.application.documenttype.usecase;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import com.mindconnect.application.documenttype.exception.DocumentTypeNotFoundApplicationException;
import com.mindconnect.domain.documenttype.event.DocumentTypeDeletedEvent;
import com.mindconnect.domain.documenttype.model.aggregate.DocumentType;
import com.mindconnect.domain.documenttype.model.valueobject.DocumentTypeId;
import com.mindconnect.domain.documenttype.port.repository.DocumentTypeRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DeleteDocumentTypeUseCaseTest {

    @Test
    void eliminaUnRegistroExistente() {
        DocumentType aggregate = DocumentType.register("valor-a", "valor-a");
        FakeDocumentTypeRepository repository = new FakeDocumentTypeRepository();
        repository.save(aggregate);
        DeleteDocumentTypeUseCase useCase = new DeleteDocumentTypeUseCase(repository);

        DocumentTypeDeletedEvent event = useCase.execute(aggregate.id());

        assertSame(aggregate, repository.deleted);
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void rechazaEliminarUnRegistroQueNoExiste() {
        FakeDocumentTypeRepository repository = new FakeDocumentTypeRepository();
        DeleteDocumentTypeUseCase useCase = new DeleteDocumentTypeUseCase(repository);

        assertThrows(DocumentTypeNotFoundApplicationException.class, () -> useCase.execute(DocumentTypeId.generate()));
        assertNull(repository.deleted);
    }

    /** Repositorio falso en memoria: así se prueba el caso de uso sin base de datos. */
    private static final class FakeDocumentTypeRepository implements DocumentTypeRepository {
        private final Map<DocumentTypeId, DocumentType> store = new LinkedHashMap<>();
        private DocumentType deleted;

        @Override
        public DocumentType save(DocumentType aggregate) {
            store.put(aggregate.id(), aggregate);
            return aggregate;
        }

        @Override
        public Optional<DocumentType> findById(DocumentTypeId id) {
            return Optional.ofNullable(store.get(id));
        }

        @Override
        public List<DocumentType> findAll() {
            return new ArrayList<>(store.values());
        }

        @Override
        public void delete(DocumentType aggregate) {
            deleted = aggregate;
            store.remove(aggregate.id());
        }
    }
}
