package com.fatloss.model;

public class BMR {

    private EnergyOptions energyOptions;

    public BMR(final EnergyOptions energyOptions) {
        this.energyOptions = energyOptions;
    }

    public double calories(final Equation equation) {
        return equation.calories();
    }
}
