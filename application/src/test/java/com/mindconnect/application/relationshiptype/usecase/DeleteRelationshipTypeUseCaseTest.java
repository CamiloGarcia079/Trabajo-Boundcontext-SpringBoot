package com.mindconnect.application.relationshiptype.usecase;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import com.mindconnect.application.relationshiptype.exception.RelationshipTypeNotFoundApplicationException;
import com.mindconnect.domain.relationshiptype.event.RelationshipTypeDeletedEvent;
import com.mindconnect.domain.relationshiptype.model.aggregate.RelationshipType;
import com.mindconnect.domain.relationshiptype.model.valueobject.RelationshipTypeId;
import com.mindconnect.domain.relationshiptype.port.repository.RelationshipTypeRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DeleteRelationshipTypeUseCaseTest {

    @Test
    void eliminaUnRegistroExistente() {
        RelationshipType aggregate = RelationshipType.register("valor-a");
        FakeRelationshipTypeRepository repository = new FakeRelationshipTypeRepository();
        repository.save(aggregate);
        DeleteRelationshipTypeUseCase useCase = new DeleteRelationshipTypeUseCase(repository);

        RelationshipTypeDeletedEvent event = useCase.execute(aggregate.id());

        assertSame(aggregate, repository.deleted);
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void rechazaEliminarUnRegistroQueNoExiste() {
        FakeRelationshipTypeRepository repository = new FakeRelationshipTypeRepository();
        DeleteRelationshipTypeUseCase useCase = new DeleteRelationshipTypeUseCase(repository);

        assertThrows(RelationshipTypeNotFoundApplicationException.class, () -> useCase.execute(RelationshipTypeId.generate()));
        assertNull(repository.deleted);
    }

    /** Repositorio falso en memoria: así se prueba el caso de uso sin base de datos. */
    private static final class FakeRelationshipTypeRepository implements RelationshipTypeRepository {
        private final Map<RelationshipTypeId, RelationshipType> store = new LinkedHashMap<>();
        private RelationshipType deleted;

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
            deleted = aggregate;
            store.remove(aggregate.id());
        }
    }
}
