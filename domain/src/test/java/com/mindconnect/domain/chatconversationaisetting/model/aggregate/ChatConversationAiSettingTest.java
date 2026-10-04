package com.mindconnect.domain.chatconversationaisetting.model.aggregate;

import java.util.UUID;

import org.junit.jupiter.api.Test;

import com.mindconnect.domain.common.exception.DomainException;
import com.mindconnect.domain.chatconversationaisetting.event.ChatConversationAiSettingRegisteredEvent;
import com.mindconnect.domain.chatconversationaisetting.event.ChatConversationAiSettingUpdatedEvent;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ChatConversationAiSettingTest {

    @Test
    void registrarDejaElEventoDeRegistro() {
        ChatConversationAiSetting aggregate = ChatConversationAiSetting.register(UUID.randomUUID(), true, UUID.randomUUID());

        assertEquals(1, aggregate.domainEvents().size());
        ChatConversationAiSettingRegisteredEvent event = assertInstanceOf(
                ChatConversationAiSettingRegisteredEvent.class, aggregate.domainEvents().getFirst());
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void registrarGeneraElIdYLosValoresPorDefecto() {
        ChatConversationAiSetting aggregate = ChatConversationAiSetting.register(UUID.randomUUID(), true, UUID.randomUUID());

        assertNotNull(aggregate.id());
        assertNotNull(aggregate.createdAt());
    }

    @Test
    void actualizarCambiaLosDatosYDejaElEventoDeActualizacion() {
        ChatConversationAiSetting aggregate = ChatConversationAiSetting.register(UUID.randomUUID(), true, UUID.randomUUID());
        aggregate.clearDomainEvents();

        aggregate.update(UUID.randomUUID(), false, UUID.randomUUID());

        assertEquals(false, aggregate.aiEnabled());
        assertEquals(1, aggregate.domainEvents().size());
        assertInstanceOf(ChatConversationAiSettingUpdatedEvent.class, aggregate.domainEvents().getFirst());
    }

    @Test
    void registrarConUnCampoObligatorioNuloLanzaDomainException() {
        assertThrows(DomainException.class, () -> ChatConversationAiSetting.register(null, true, UUID.randomUUID()));
    }
}
