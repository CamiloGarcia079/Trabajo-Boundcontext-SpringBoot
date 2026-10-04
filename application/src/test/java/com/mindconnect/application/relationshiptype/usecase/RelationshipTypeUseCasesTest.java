package com.mindconnect.application.relationshiptype.usecase;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mindconnect.application.relationshiptype.command.RegisterRelationshipTypeCommand;
import com.mindconnect.application.relationshiptype.command.UpdateRelationshipTypeCommand;
import com.mindconnect.application.relationshiptype.dto.RelationshipTypeResponse;
import com.mindconnect.application.relationshiptype.exception.RelationshipTypeNotFoundApplicationException;
import com.mindconnect.domain.relationshiptype.model.aggregate.RelationshipType;
import com.mindconnect.domain.relationshiptype.model.valueobject.RelationshipTypeId;
import com.mindconnect.domain.relationshiptype.port.repository.RelationshipTypeRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RelationshipTypeUseCasesTest {

    private FakeRelationshipTypeRepository repository;
    private RegisterRelationshipTypeUseCase register;
    private GetRelationshipTypeByIdUseCase getById;
    private ListRelationshipTypeUseCase list;
    private UpdateRelationshipTypeUseCase update;

    @BeforeEach
    void setUp() {
        repository = new FakeRelationshipTypeRepository();
        register = new RegisterRelationshipTypeUseCase(repository);
        getById = new GetRelationshipTypeByIdUseCase(repository);
        list = new ListRelationshipTypeUseCase(repository);
        update = new UpdateRelationshipTypeUseCase(repository);
    }

    @Test
    void registrarGuardaYDevuelveElRegistroConIdGenerado() {
        RelationshipTypeResponse created = register.execute(commandA());

        assertNotNull(created.id());
        assertEquals(1, repository.findAll().size());
    }

    @Test
    void buscarPorIdDevuelveLoGuardado() {
        RelationshipTypeResponse created = register.execute(commandA());

        assertEquals(created, getById.execute(new RelationshipTypeId(created.id())));
    }

    @Test
    void buscarUnIdInexistenteLanzaNotFound() {
        assertThrows(RelationshipTypeNotFoundApplicationException.class, () -> getById.execute(RelationshipTypeId.generate()));
    }

    @Test
    void listarDevuelveTodosLosRegistros() {
        register.execute(commandA());
        register.execute(commandA());

        assertEquals(2, list.execute().size());
    }

    @Test
    void actualizarConservaElId() {
        RelationshipTypeResponse created = register.execute(commandA());

        RelationshipTypeResponse updated = update.execute(updateCommand(new RelationshipTypeId(created.id())));

        assertEquals(created.id(), updated.id());
        assertEquals(updated, getById.execute(new RelationshipTypeId(created.id())));
    }

    @Test
    void actualizarUnIdInexistenteLanzaNotFound() {
        assertThrows(RelationshipTypeNotFoundApplicationException.class, () -> update.execute(updateCommand(RelationshipTypeId.generate())));
    }

    private RegisterRelationshipTypeCommand commandA() {
        return new RegisterRelationshipTypeCommand("valor-a");
    }

    private UpdateRelationshipTypeCommand updateCommand(RelationshipTypeId id) {
        return new UpdateRelationshipTypeCommand(id, "valor-b");
    }

    /** Repositorio falso en memoria: así se prueba el caso de uso sin base de datos. */
    private static final class FakeRelationshipTypeRepository implements RelationshipTypeRepository {
        private final Map<RelationshipTypeId, RelationshipType> store = new LinkedHashMap<>();

        @Override
        public RelationshipType save(RelationshipType aggregate) {
            store.put(aggregate.id(), aggregate);
            return aggregate;
        }

        @Override
        public Optional<RelationshipType> findById(RelationshipTypeId id) {
            return Optional.ofNullable(store.get(id));
        }

        @Override
        public List<RelationshipType> findAll() {
            return new ArrayList<>(store.values());
        }

        @Override
        public void delete(RelationshipType aggregate) {
            store.remove(aggregate.id());
        }
    }
}
