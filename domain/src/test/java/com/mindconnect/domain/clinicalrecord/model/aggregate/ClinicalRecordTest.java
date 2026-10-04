package com.mindconnect.domain.clinicalrecord.model.aggregate;

import java.time.LocalDateTime;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import com.mindconnect.domain.common.exception.DomainException;
import com.mindconnect.domain.clinicalrecord.event.ClinicalRecordRegisteredEvent;
import com.mindconnect.domain.clinicalrecord.event.ClinicalRecordUpdatedEvent;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ClinicalRecordTest {

    @Test
    void registrarDejaElEventoDeRegistro() {
        ClinicalRecord aggregate = ClinicalRecord.register(UUID.randomUUID(), LocalDateTime.now(), "valor-a", LocalDateTime.now(), LocalDateTime.now(), UUID.randomUUID(), UUID.randomUUID());

        assertEquals(1, aggregate.domainEvents().size());
        ClinicalRecordRegisteredEvent event = assertInstanceOf(
                ClinicalRecordRegisteredEvent.class, aggregate.domainEvents().getFirst());
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void registrarGeneraElIdYLosValoresPorDefecto() {
        ClinicalRecord aggregate = ClinicalRecord.register(UUID.randomUUID(), LocalDateTime.now(), "valor-a", LocalDateTime.now(), LocalDateTime.now(), UUID.randomUUID(), UUID.randomUUID());

        assertNotNull(aggregate.id());
        assertNotNull(aggregate.createdAt());
    }

    @Test
    void actualizarCambiaLosDatosYDejaElEventoDeActualizacion() {
        ClinicalRecord aggregate = ClinicalRecord.register(UUID.randomUUID(), LocalDateTime.now(), "valor-a", LocalDateTime.now(), LocalDateTime.now(), UUID.randomUUID(), UUID.randomUUID());
        aggregate.clearDomainEvents();

        aggregate.update(UUID.randomUUID(), LocalDateTime.now(), "valor-b", LocalDateTime.now(), LocalDateTime.now(), UUID.randomUUID());

        assertEquals("valor-b", aggregate.recordNumber());
        assertEquals(1, aggregate.domainEvents().size());
        assertInstanceOf(ClinicalRecordUpdatedEvent.class, aggregate.domainEvents().getFirst());
    }

    @Test
    void registrarConUnCampoObligatorioNuloLanzaDomainException() {
        assertThrows(DomainException.class, () -> ClinicalRecord.register(null, LocalDateTime.now(), "valor-a", LocalDateTime.now(), LocalDateTime.now(), UUID.randomUUID(), UUID.randomUUID()));
    }
}
