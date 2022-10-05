package com.fatloss.model;

public enum Gender {
    FEMALE("F"),
    MALE("M")
    ;

    private String gender;

    Gender(final String gender) {
        this.gender = gender;
    }

    public String gender() {
        return this.gender;
    }
}