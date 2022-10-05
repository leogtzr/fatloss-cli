package com.fatloss.model;

public class EnergyOptions {

    private int age;
    private double weight;
    private double height;
    private Gender gender;

    public int getAge() {
        return age;
    }

    public void setAge(final int age) {
        this.age = age;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(final double weight) {
        this.weight = weight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(final double height) {
        this.height = height;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(final Gender gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "EnergyOptions{" +
                "age=" + age +
                ", weight=" + weight +
                ", height=" + height +
                ", gender=" + gender +
                '}';
    }
}
