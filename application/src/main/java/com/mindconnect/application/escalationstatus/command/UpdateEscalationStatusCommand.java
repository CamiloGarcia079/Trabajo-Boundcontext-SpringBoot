package com.mindconnect.application.escalationstatus.command;


import com.mindconnect.domain.escalationstatus.model.valueobject.EscalationStatusId;

public record UpdateEscalationStatusCommand(
        EscalationStatusId id,
        String nameStatus
) {
}
