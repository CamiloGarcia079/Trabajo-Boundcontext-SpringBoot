package com.mindconnect.application.documenttype.usecase;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mindconnect.application.documenttype.command.RegisterDocumentTypeCommand;
import com.mindconnect.application.documenttype.command.UpdateDocumentTypeCommand;
import com.mindconnect.application.documenttype.dto.DocumentTypeResponse;
import com.mindconnect.application.documenttype.exception.DocumentTypeNotFoundApplicationException;
import com.mindconnect.domain.documenttype.model.aggregate.DocumentType;
import com.mindconnect.domain.documenttype.model.valueobject.DocumentTypeId;
import com.mindconnect.domain.documenttype.port.repository.DocumentTypeRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DocumentTypeUseCasesTest {

    private FakeDocumentTypeRepository repository;
    private RegisterDocumentTypeUseCase register;
    private GetDocumentTypeByIdUseCase getById;
    private ListDocumentTypeUseCase list;
    private UpdateDocumentTypeUseCase update;

    @BeforeEach
    void setUp() {
        repository = new FakeDocumentTypeRepository();
        register = new RegisterDocumentTypeUseCase(repository);
        getById = new GetDocumentTypeByIdUseCase(repository);
        list = new ListDocumentTypeUseCase(repository);
        update = new UpdateDocumentTypeUseCase(repository);
    }

    @Test
    void registrarGuardaYDevuelveElRegistroConIdGenerado() {
        DocumentTypeResponse created = register.execute(commandA());

        assertNotNull(created.id());
        assertEquals(1, repository.findAll().size());
    }

    @Test
    void buscarPorIdDevuelveLoGuardado() {
        DocumentTypeResponse created = register.execute(commandA());

        assertEquals(created, getById.execute(new DocumentTypeId(created.id())));
    }

    @Test
    void buscarUnIdInexistenteLanzaNotFound() {
        assertThrows(DocumentTypeNotFoundApplicationException.class, () -> getById.execute(DocumentTypeId.generate()));
    }

    @Test
    void listarDevuelveTodosLosRegistros() {
        register.execute(commandA());
        register.execute(commandA());

        assertEquals(2, list.execute().size());
    }

    @Test
    void actualizarConservaElId() {
        DocumentTypeResponse created = register.execute(commandA());

        DocumentTypeResponse updated = update.execute(updateCommand(new DocumentTypeId(created.id())));

        assertEquals(created.id(), updated.id());
        assertEquals(updated, getById.execute(new DocumentTypeId(created.id())));
    }

    @Test
    void actualizarUnIdInexistenteLanzaNotFound() {
        assertThrows(DocumentTypeNotFoundApplicationException.class, () -> update.execute(updateCommand(DocumentTypeId.generate())));
    }

    private RegisterDocumentTypeCommand commandA() {
        return new RegisterDocumentTypeCommand("valor-a", "valor-a");
    }

    private UpdateDocumentTypeCommand updateCommand(DocumentTypeId id) {
        return new UpdateDocumentTypeCommand(id, "valor-b", "valor-b");
    }

    /** Repositorio falso en memoria: así se prueba el caso de uso sin base de datos. */
    private static final class FakeDocumentTypeRepository implements DocumentTypeRepository {
        private final Map<DocumentTypeId, DocumentType> store = new LinkedHashMap<>();

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
            store.remove(aggregate.id());
        }
    }
}
