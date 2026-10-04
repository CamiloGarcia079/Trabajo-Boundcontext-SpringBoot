package com.mindconnect.application.airunstatus.command;


import com.mindconnect.domain.airunstatus.model.valueobject.AiRunStatusId;

public record UpdateAiRunStatusCommand(
        AiRunStatusId id,
        String nameStatus
) {
}
