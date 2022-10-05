package com.fatloss.model;

public enum ActivityFactor {
    SEDENTARY(1.2, "Sedentary (little or no exercise)"),
    LIGHTLY_ACTIVE(1.375, "Lightly active (light exercise/sports 1-3 days/week)"),
    MODERATELY_ACTIVE(1.55, "Moderately active (moderate exercise/sports 3-5 days/week)"),
    VERY_ACTIVE(1.725, "Very active (hard exercise/sports 6-7 days a week)"),
    EXTRA_ACTIVE(1.9, "Very hard exercise/sports & a physical job")
    ;

    private double factor;
    private String description;

    ActivityFactor(final double factor, final String description) {
        this.factor = factor;
        this.description = description;
    }

    public double factor() {
        return this.factor;
    }

    public String description() {
        return this.description;
    }
}
