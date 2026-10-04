package com.mindconnect.application.risklevel.command;


public record RegisterRiskLevelCommand(
        String code,
        String name,
        Integer severity
) {
}
