package com.example.ejercicioPractico.domain.enums;

public enum Gender {
    MASCULINO("M"),
    FEMENINO("F"),
    OTRO("O");

    private final String gender;

    Gender(String gender) { this.gender = gender; }
    public String getGender() { return gender; }

}
