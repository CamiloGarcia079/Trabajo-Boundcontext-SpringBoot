package com.mindconnect.application.chatescalation.usecase;

import java.util.UUID;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import com.mindconnect.application.chatescalation.exception.ChatEscalationNotFoundApplicationException;
import com.mindconnect.domain.chatescalation.event.ChatEscalationDeletedEvent;
import com.mindconnect.domain.chatescalation.model.aggregate.ChatEscalation;
import com.mindconnect.domain.chatescalation.model.valueobject.ChatEscalationId;
import com.mindconnect.domain.chatescalation.port.repository.ChatEscalationRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DeleteChatEscalationUseCaseTest {

    @Test
    void eliminaUnRegistroExistente() {
        ChatEscalation aggregate = ChatEscalation.register(UUID.randomUUID(), UUID.randomUUID(), true, "valor-a");
        FakeChatEscalationRepository repository = new FakeChatEscalationRepository();
        repository.save(aggregate);
        DeleteChatEscalationUseCase useCase = new DeleteChatEscalationUseCase(repository);

        ChatEscalationDeletedEvent event = useCase.execute(aggregate.id());

        assertSame(aggregate, repository.deleted);
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void rechazaEliminarUnRegistroQueNoExiste() {
        FakeChatEscalationRepository repository = new FakeChatEscalationRepository();
        DeleteChatEscalationUseCase useCase = new DeleteChatEscalationUseCase(repository);

        assertThrows(ChatEscalationNotFoundApplicationException.class, () -> useCase.execute(ChatEscalationId.generate()));
        assertNull(repository.deleted);
    }

    /** Repositorio falso en memoria: así se prueba el caso de uso sin base de datos. */
    private static final class FakeChatEscalationRepository implements ChatEscalationRepository {
        private final Map<ChatEscalationId, ChatEscalation> store = new LinkedHashMap<>();
        private ChatEscalation deleted;

        @Override
        public ChatEscalation save(ChatEscalation aggregate) {
            store.put(aggregate.id(), aggregate);
            return aggregate;
        }

        @Override
        public Optional<ChatEscalation> findById(ChatEscalationId id) {
            return Optional.ofNullable(store.get(id));
        }

        @Override
        public List<ChatEscalation> findAll() {
            return new ArrayList<>(store.values());
        }

        @Override
        public void delete(ChatEscalation aggregate) {
            deleted = aggregate;
            store.remove(aggregate.id());
        }
    }
}
