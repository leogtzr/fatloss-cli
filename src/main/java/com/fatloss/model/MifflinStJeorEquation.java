package com.fatloss.model;

public class MifflinStJeorEquation implements Equation {

    private EnergyOptions energyOptions;

    public MifflinStJeorEquation(final EnergyOptions energyOptions) {
        this.energyOptions = energyOptions;
    }

    @Override
    public double calories() {
        if (this.energyOptions.getGender() == Gender.FEMALE) {
            return (10 * this.energyOptions.getWeight()) + (6.25 * this.energyOptions.getHeight()) -
                    (5 * this.energyOptions.getAge()) - 161;
        }

        return (10 * this.energyOptions.getWeight()) + (6.25 * this.energyOptions.getHeight()) -
                (5 * this.energyOptions.getAge()) + 5;
    }
}
