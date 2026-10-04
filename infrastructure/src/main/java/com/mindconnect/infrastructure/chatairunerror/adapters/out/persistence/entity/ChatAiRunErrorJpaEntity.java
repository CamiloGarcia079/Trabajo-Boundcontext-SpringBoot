package com.mindconnect.infrastructure.chatairunerror.adapters.out.persistence.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Entidad JPA de la tabla chat_ai_run_errors. Vive solo en infraestructura: el dominio no la conoce.
 *
 * <p>Las llaves foráneas se guardan como UUID simples (sin @ManyToOne), igual que en el
 * modelo de dominio, que se relaciona con otros agregados solo por id.</p>
 */
@Entity
@Table(name = "chat_ai_run_errors")
public class ChatAiRunErrorJpaEntity {

    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "ai_run_id", nullable = false)
    private UUID aiRunId;

    @Column(name = "error_message", nullable = false, columnDefinition = "TEXT")
    private String errorMessage;

    @Column(name = "error_code", nullable = false, length = 80)
    private String errorCode;

    @Column(name = "provider_error_id", nullable = false, length = 120)
    private String providerErrorId;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    /** JPA exige un constructor sin argumentos. */
    public ChatAiRunErrorJpaEntity() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getAiRunId() {
        return aiRunId;
    }

    public void setAiRunId(UUID aiRunId) {
        this.aiRunId = aiRunId;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getProviderErrorId() {
        return providerErrorId;
    }

    public void setProviderErrorId(String providerErrorId) {
        this.providerErrorId = providerErrorId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
