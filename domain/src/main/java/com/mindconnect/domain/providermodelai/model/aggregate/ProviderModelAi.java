package com.mindconnect.domain.providermodelai.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;

import com.mindconnect.domain.common.model.AggregateRoot;
import com.mindconnect.domain.common.validation.DomainValidations;
import com.mindconnect.domain.providermodelai.event.ProviderModelAiRegisteredEvent;
import com.mindconnect.domain.providermodelai.event.ProviderModelAiUpdatedEvent;
import com.mindconnect.domain.providermodelai.model.valueobject.ProviderModelAiId;

/**
 * Agregado raíz del contexto providermodelai: representa la tabla provider_models_ai.
 *
 * <p>El id y las fechas los pone el propio agregado. No usa Spring ni JPA.</p>
 */
public class ProviderModelAi extends AggregateRoot {

    private final ProviderModelAiId id;
    private String nameProviderAi;
    private String razonSocial;
    private String sitioWeb;
    private boolean isActive;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private ProviderModelAi(
            ProviderModelAiId id,
            String nameProviderAi,
            String razonSocial,
            String sitioWeb,
            boolean isActive,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        DomainValidations.required(nameProviderAi, "nameProviderAi");
        DomainValidations.required(razonSocial, "razonSocial");
        DomainValidations.required(sitioWeb, "sitioWeb");
        DomainValidations.required(createdAt, "createdAt");
        DomainValidations.required(updatedAt, "updatedAt");
        this.nameProviderAi = nameProviderAi;
        this.razonSocial = razonSocial;
        this.sitioWeb = sitioWeb;
        this.isActive = isActive;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    /** Crea un registro nuevo: genera el id y las fechas, y deja el evento de registro. */
    public static ProviderModelAi register(
            String nameProviderAi,
            String razonSocial,
            String sitioWeb) {
        ProviderModelAiId id = ProviderModelAiId.generate();
        LocalDateTime now = LocalDateTime.now();
        ProviderModelAi aggregate = new ProviderModelAi(id, nameProviderAi, razonSocial, sitioWeb, true, now, now);
        aggregate.recordEvent(new ProviderModelAiRegisteredEvent(id, now));
        return aggregate;
    }

    /** Reconstruye un registro que ya existía (viene de la base de datos). No genera eventos. */
    public static ProviderModelAi restore(
            ProviderModelAiId id,
            String nameProviderAi,
            String razonSocial,
            String sitioWeb,
            boolean isActive,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        return new ProviderModelAi(id, nameProviderAi, razonSocial, sitioWeb, isActive, createdAt, updatedAt);
    }

    /** Cambia los datos del registro, actualiza la fecha de modificación y deja el evento. */
    public void update(
            String nameProviderAi,
            String razonSocial,
            String sitioWeb) {
        DomainValidations.required(nameProviderAi, "nameProviderAi");
        DomainValidations.required(razonSocial, "razonSocial");
        DomainValidations.required(sitioWeb, "sitioWeb");
        this.nameProviderAi = nameProviderAi;
        this.razonSocial = razonSocial;
        this.sitioWeb = sitioWeb;
        LocalDateTime now = LocalDateTime.now();
        this.updatedAt = now;
        recordEvent(new ProviderModelAiUpdatedEvent(this.id, now));
    }

    public ProviderModelAiId id() { return id; }
    public String nameProviderAi() { return nameProviderAi; }
    public String razonSocial() { return razonSocial; }
    public String sitioWeb() { return sitioWeb; }
    public boolean isActive() { return isActive; }
    public LocalDateTime createdAt() { return createdAt; }
    public LocalDateTime updatedAt() { return updatedAt; }
}
