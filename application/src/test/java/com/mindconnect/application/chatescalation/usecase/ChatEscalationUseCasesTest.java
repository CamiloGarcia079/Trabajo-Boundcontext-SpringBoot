package com.mindconnect.application.chatescalation.usecase;

import java.util.UUID;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mindconnect.application.chatescalation.command.RegisterChatEscalationCommand;
import com.mindconnect.application.chatescalation.command.UpdateChatEscalationCommand;
import com.mindconnect.application.chatescalation.dto.ChatEscalationResponse;
import com.mindconnect.application.chatescalation.exception.ChatEscalationNotFoundApplicationException;
import com.mindconnect.domain.chatescalation.model.aggregate.ChatEscalation;
import com.mindconnect.domain.chatescalation.model.valueobject.ChatEscalationId;
import com.mindconnect.domain.chatescalation.port.repository.ChatEscalationRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ChatEscalationUseCasesTest {

    private FakeChatEscalationRepository repository;
    private RegisterChatEscalationUseCase register;
    private GetChatEscalationByIdUseCase getById;
    private ListChatEscalationUseCase list;
    private UpdateChatEscalationUseCase update;

    @BeforeEach
    void setUp() {
        repository = new FakeChatEscalationRepository();
        register = new RegisterChatEscalationUseCase(repository);
        getById = new GetChatEscalationByIdUseCase(repository);
        list = new ListChatEscalationUseCase(repository);
        update = new UpdateChatEscalationUseCase(repository);
    }

    @Test
    void registrarGuardaYDevuelveElRegistroConIdGenerado() {
        ChatEscalationResponse created = register.execute(commandA());

        assertNotNull(created.id());
        assertEquals(1, repository.findAll().size());
    }

    @Test
    void buscarPorIdDevuelveLoGuardado() {
        ChatEscalationResponse created = register.execute(commandA());

        assertEquals(created, getById.execute(new ChatEscalationId(created.id())));
    }

    @Test
    void buscarUnIdInexistenteLanzaNotFound() {
        assertThrows(ChatEscalationNotFoundApplicationException.class, () -> getById.execute(ChatEscalationId.generate()));
    }

    @Test
    void listarDevuelveTodosLosRegistros() {
        register.execute(commandA());
        register.execute(commandA());

        assertEquals(2, list.execute().size());
    }

    @Test
    void actualizarConservaElId() {
        ChatEscalationResponse created = register.execute(commandA());

        ChatEscalationResponse updated = update.execute(updateCommand(new ChatEscalationId(created.id())));

        assertEquals(created.id(), updated.id());
        assertEquals(updated, getById.execute(new ChatEscalationId(created.id())));
    }

    @Test
    void actualizarUnIdInexistenteLanzaNotFound() {
        assertThrows(ChatEscalationNotFoundApplicationException.class, () -> update.execute(updateCommand(ChatEscalationId.generate())));
    }

    private RegisterChatEscalationCommand commandA() {
        return new RegisterChatEscalationCommand(UUID.randomUUID(), UUID.randomUUID(), true, "valor-a");
    }

    private UpdateChatEscalationCommand updateCommand(ChatEscalationId id) {
        return new UpdateChatEscalationCommand(id, UUID.randomUUID(), UUID.randomUUID(), false, "valor-b");
    }

    /** Repositorio falso en memoria: así se prueba el caso de uso sin base de datos. */
    private static final class FakeChatEscalationRepository implements ChatEscalationRepository {
        private final Map<ChatEscalationId, ChatEscalation> store = new LinkedHashMap<>();

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
            store.remove(aggregate.id());
        }
    }
}
