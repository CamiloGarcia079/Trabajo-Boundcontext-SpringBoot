package com.mindconnect.application.chatparticipant.usecase;

import java.util.UUID;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mindconnect.application.chatparticipant.command.RegisterChatParticipantCommand;
import com.mindconnect.application.chatparticipant.command.UpdateChatParticipantCommand;
import com.mindconnect.application.chatparticipant.dto.ChatParticipantResponse;
import com.mindconnect.application.chatparticipant.exception.ChatParticipantNotFoundApplicationException;
import com.mindconnect.domain.chatparticipant.model.aggregate.ChatParticipant;
import com.mindconnect.domain.chatparticipant.model.valueobject.ChatParticipantId;
import com.mindconnect.domain.chatparticipant.port.repository.ChatParticipantRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ChatParticipantUseCasesTest {

    private FakeChatParticipantRepository repository;
    private RegisterChatParticipantUseCase register;
    private GetChatParticipantByIdUseCase getById;
    private ListChatParticipantUseCase list;
    private UpdateChatParticipantUseCase update;

    @BeforeEach
    void setUp() {
        repository = new FakeChatParticipantRepository();
        register = new RegisterChatParticipantUseCase(repository);
        getById = new GetChatParticipantByIdUseCase(repository);
        list = new ListChatParticipantUseCase(repository);
        update = new UpdateChatParticipantUseCase(repository);
    }

    @Test
    void registrarGuardaYDevuelveElRegistroConIdGenerado() {
        ChatParticipantResponse created = register.execute(commandA());

        assertNotNull(created.id());
        assertEquals(1, repository.findAll().size());
    }

    @Test
    void buscarPorIdDevuelveLoGuardado() {
        ChatParticipantResponse created = register.execute(commandA());

        assertEquals(created, getById.execute(new ChatParticipantId(created.id())));
    }

    @Test
    void buscarUnIdInexistenteLanzaNotFound() {
        assertThrows(ChatParticipantNotFoundApplicationException.class, () -> getById.execute(ChatParticipantId.generate()));
    }

    @Test
    void listarDevuelveTodosLosRegistros() {
        register.execute(commandA());
        register.execute(commandA());

        assertEquals(2, list.execute().size());
    }

    @Test
    void actualizarConservaElId() {
        ChatParticipantResponse created = register.execute(commandA());

        ChatParticipantResponse updated = update.execute(updateCommand(new ChatParticipantId(created.id())));

        assertEquals(created.id(), updated.id());
        assertEquals(updated, getById.execute(new ChatParticipantId(created.id())));
    }

    @Test
    void actualizarUnIdInexistenteLanzaNotFound() {
        assertThrows(ChatParticipantNotFoundApplicationException.class, () -> update.execute(updateCommand(ChatParticipantId.generate())));
    }

    private RegisterChatParticipantCommand commandA() {
        return new RegisterChatParticipantCommand(UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID());
    }

    private UpdateChatParticipantCommand updateCommand(ChatParticipantId id) {
        return new UpdateChatParticipantCommand(id, UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID());
    }

    /** Repositorio falso en memoria: así se prueba el caso de uso sin base de datos. */
    private static final class FakeChatParticipantRepository implements ChatParticipantRepository {
        private final Map<ChatParticipantId, ChatParticipant> store = new LinkedHashMap<>();

        @Override
        public ChatParticipant save(ChatParticipant aggregate) {
            store.put(aggregate.id(), aggregate);
            return aggregate;
        }

        @Override
        public Optional<ChatParticipant> findById(ChatParticipantId id) {
            return Optional.ofNullable(store.get(id));
        }

        @Override
        public List<ChatParticipant> findAll() {
            return new ArrayList<>(store.values());
        }

        @Override
        public void delete(ChatParticipant aggregate) {
            store.remove(aggregate.id());
        }
    }
}
