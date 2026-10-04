package com.mindconnect.application.chatescalationassignment.usecase;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import com.mindconnect.application.chatescalationassignment.exception.ChatEscalationAssignmentNotFoundApplicationException;
import com.mindconnect.domain.chatescalationassignment.event.ChatEscalationAssignmentDeletedEvent;
import com.mindconnect.domain.chatescalationassignment.model.aggregate.ChatEscalationAssignment;
import com.mindconnect.domain.chatescalationassignment.model.valueobject.ChatEscalationAssignmentId;
import com.mindconnect.domain.chatescalationassignment.port.repository.ChatEscalationAssignmentRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DeleteChatEscalationAssignmentUseCaseTest {

    @Test
    void eliminaUnRegistroExistente() {
        ChatEscalationAssignment aggregate = ChatEscalationAssignment.register(UUID.randomUUID(), UUID.randomUUID(), LocalDateTime.now());
        FakeChatEscalationAssignmentRepository repository = new FakeChatEscalationAssignmentRepository();
        repository.save(aggregate);
        DeleteChatEscalationAssignmentUseCase useCase = new DeleteChatEscalationAssignmentUseCase(repository);

        ChatEscalationAssignmentDeletedEvent event = useCase.execute(aggregate.id());

        assertSame(aggregate, repository.deleted);
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void rechazaEliminarUnRegistroQueNoExiste() {
        FakeChatEscalationAssignmentRepository repository = new FakeChatEscalationAssignmentRepository();
        DeleteChatEscalationAssignmentUseCase useCase = new DeleteChatEscalationAssignmentUseCase(repository);

        assertThrows(ChatEscalationAssignmentNotFoundApplicationException.class, () -> useCase.execute(ChatEscalationAssignmentId.generate()));
        assertNull(repository.deleted);
    }

    /** Repositorio falso en memoria: así se prueba el caso de uso sin base de datos. */
    private static final class FakeChatEscalationAssignmentRepository implements ChatEscalationAssignmentRepository {
        private final Map<ChatEscalationAssignmentId, ChatEscalationAssignment> store = new LinkedHashMap<>();
        private ChatEscalationAssignment deleted;

        @Override
        public ChatEscalationAssignment save(ChatEscalationAssignment aggregate) {
            store.put(aggregate.id(), aggregate);
            return aggregate;
        }

        @Override
        public Optional<ChatEscalationAssignment> findById(ChatEscalationAssignmentId id) {
            return Optional.ofNullable(store.get(id));
        }

        @Override
        public List<ChatEscalationAssignment> findAll() {
            return new ArrayList<>(store.values());
        }

        @Override
        public void delete(ChatEscalationAssignment aggregate) {
            deleted = aggregate;
            store.remove(aggregate.id());
        }
    }
}
