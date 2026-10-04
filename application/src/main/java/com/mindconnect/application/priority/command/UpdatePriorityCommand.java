package com.mindconnect.application.priority.command;


import com.mindconnect.domain.priority.model.valueobject.PriorityId;

public record UpdatePriorityCommand(
        PriorityId id,
        String namePriority
) {
}
