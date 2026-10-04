package com.mindconnect.application.risklevel.command;


import com.mindconnect.domain.risklevel.model.valueobject.RiskLevelId;

public record UpdateRiskLevelCommand(
        RiskLevelId id,
        String code,
        String name,
        Integer severity
) {
}
