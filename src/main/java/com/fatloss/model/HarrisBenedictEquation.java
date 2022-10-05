package com.fatloss.model;

public class HarrisBenedictEquation implements Equation {

    private EnergyOptions energyOptions;

    public HarrisBenedictEquation(final EnergyOptions energyOptions) {
        this.energyOptions = energyOptions;
    }

    @Override
    public double calories() {
        if (this.energyOptions.getGender() == Gender.FEMALE) {
            return 447.593 + (9.247 * this.energyOptions.getWeight()) +
                    (3.098 * this.energyOptions.getHeight()) - (4.330 * this.energyOptions.getAge());
        }

        return 88.362 + (13.397 * this.energyOptions.getWeight()) + (4.799 * this.energyOptions.getHeight()) -
                (5.677 * this.energyOptions.getAge());
    }
}
