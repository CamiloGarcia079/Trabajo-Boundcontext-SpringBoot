CREATE TABLE IF NOT EXISTS ${db_schema}.treatment_goals (
    id UUID PRIMARY KEY,
    treatment_plan_id UUID NOT NULL,
    description TEXT NOT NULL,
    target_date DATE NOT NULL,
    completed_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    notes TEXT NOT NULL,
    treatment_goal_id UUID NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT fk_treatment_goals_treatment_plan_id FOREIGN KEY (treatment_plan_id) REFERENCES ${db_schema}.treatment_plans (id),
    CONSTRAINT fk_treatment_goals_treatment_goal_id FOREIGN KEY (treatment_goal_id) REFERENCES ${db_schema}.treatment_goal_statuses (id)
);

CREATE INDEX IF NOT EXISTS idx_treatment_goals_treatment_plan_id ON ${db_schema}.treatment_goals (treatment_plan_id);
CREATE INDEX IF NOT EXISTS idx_treatment_goals_treatment_goal_id ON ${db_schema}.treatment_goals (treatment_goal_id);
