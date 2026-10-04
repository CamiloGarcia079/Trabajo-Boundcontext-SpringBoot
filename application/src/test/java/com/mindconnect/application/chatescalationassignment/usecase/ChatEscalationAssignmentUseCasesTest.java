package com.mindconnect.application.chatescalationassignment.usecase;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mindconnect.application.chatescalationassignment.command.RegisterChatEscalationAssignmentCommand;
import com.mindconnect.application.chatescalationassignment.command.UpdateChatEscalationAssignmentCommand;
import com.mindconnect.application.chatescalationassignment.dto.ChatEscalationAssignmentResponse;
import com.mindconnect.application.chatescalationassignment.exception.ChatEscalationAssignmentNotFoundApplicationException;
import com.mindconnect.domain.chatescalationassignment.model.aggregate.ChatEscalationAssignment;
import com.mindconnect.domain.chatescalationassignment.model.valueobject.ChatEscalationAssignmentId;
import com.mindconnect.domain.chatescalationassignment.port.repository.ChatEscalationAssignmentRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ChatEscalationAssignmentUseCasesTest {

    private FakeChatEscalationAssignmentRepository repository;
    private RegisterChatEscalationAssignmentUseCase register;
    private GetChatEscalationAssignmentByIdUseCase getById;
    private ListChatEscalationAssignmentUseCase list;
    private UpdateChatEscalationAssignmentUseCase update;

    @BeforeEach
    void setUp() {
        repository = new FakeChatEscalationAssignmentRepository();
        register = new RegisterChatEscalationAssignmentUseCase(repository);
        getById = new GetChatEscalationAssignmentByIdUseCase(repository);
        list = new ListChatEscalationAssignmentUseCase(repository);
        update = new UpdateChatEscalationAssignmentUseCase(repository);
    }

    @Test
    void registrarGuardaYDevuelveElRegistroConIdGenerado() {
        ChatEscalationAssignmentResponse created = register.execute(commandA());

        assertNotNull(created.id());
        assertEquals(1, repository.findAll().size());
    }

    @Test
    void buscarPorIdDevuelveLoGuardado() {
        ChatEscalationAssignmentResponse created = register.execute(commandA());

        assertEquals(created, getById.execute(new ChatEscalationAssignmentId(created.id())));
    }

    @Test
    void buscarUnIdInexistenteLanzaNotFound() {
        assertThrows(ChatEscalationAssignmentNotFoundApplicationException.class, () -> getById.execute(ChatEscalationAssignmentId.generate()));
    }

    @Test
    void listarDevuelveTodosLosRegistros() {
        register.execute(commandA());
        register.execute(commandA());

        assertEquals(2, list.execute().size());
    }

    @Test
    void actualizarConservaElId() {
        ChatEscalationAssignmentResponse created = register.execute(commandA());

        ChatEscalationAssignmentResponse updated = update.execute(updateCommand(new ChatEscalationAssignmentId(created.id())));

        assertEquals(created.id(), updated.id());
        assertEquals(updated, getById.execute(new ChatEscalationAssignmentId(created.id())));
    }

    @Test
    void actualizarUnIdInexistenteLanzaNotFound() {
        assertThrows(ChatEscalationAssignmentNotFoundApplicationException.class, () -> update.execute(updateCommand(ChatEscalationAssignmentId.generate())));
    }

    private RegisterChatEscalationAssignmentCommand commandA() {
        return new RegisterChatEscalationAssignmentCommand(UUID.randomUUID(), UUID.randomUUID(), LocalDateTime.now());
    }

    private UpdateChatEscalationAssignmentCommand updateCommand(ChatEscalationAssignmentId id) {
        return new UpdateChatEscalationAssignmentCommand(id, UUID.randomUUID(), UUID.randomUUID(), LocalDateTime.now());
    }

    /** Repositorio falso en memoria: así se prueba el caso de uso sin base de datos. */
    private static final class FakeChatEscalationAssignmentRepository implements ChatEscalationAssignmentRepository {
        private final Map<ChatEscalationAssignmentId, ChatEscalationAssignment> store = new LinkedHashMap<>();

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
            store.remove(aggregate.id());
        }
    }
}
