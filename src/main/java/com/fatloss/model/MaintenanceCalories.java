package com.fatloss.model;

public class MaintenanceCalories {

    private EnergyOptions energyOptions;
    private ActivityFactor activityFactor;

    public MaintenanceCalories(final EnergyOptions energyOptions, final ActivityFactor activityFactor) {
        this.energyOptions = energyOptions;
        this.activityFactor = activityFactor;
    }

    public double calories(final Equation equation) {
        return equation.calories() * this.activityFactor.factor();
    }

    @Override
    public String toString() {
        return "MaintenanceCalories{" +
                "energyOptions=" + energyOptions +
                ", activityFactor=" + activityFactor +
                '}';
    }
}
